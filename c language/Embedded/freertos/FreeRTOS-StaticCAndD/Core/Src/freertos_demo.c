/*
 * freertos_demo.c
 *
 *  Created on: Feb 20, 2025
 *      Author: A3452
 */

#include "freertos_demo.h"
#include "FreeRTOS.h"
#include "task.h"
#include "stdio.h"
#include "gpio.h"

#define START_TASK_STACK 128
#define START_TASK_PRIORITY 1
TaskHandle_t startTaskHandle;
static void StartTask(void *pvParameters);

#define TASK1_STACK 128
#define TASK1_PRIORITY 1
TaskHandle_t Task1Handle;
static void Task1(void *pvParameters);

#define TASK2_STACK 128
#define TASK2_PRIORITY 1
TaskHandle_t Task2Handle;
static void Task2(void *pvParameters);

#define TASK3_STACK 128
#define TASK3_PRIORITY 1
TaskHandle_t Task3Handle;
static void Task3(void *pvParameters);

static void StartTask(void *pvParameters)
{
	taskENTER_CRITICAL();
	//Create three tasks.
	xTaskCreate(Task1, "Task1", TASK1_STACK, NULL, TASK1_PRIORITY, &Task1Handle);
	xTaskCreate(Task2, "Task2", TASK2_STACK, NULL, TASK2_PRIORITY, &Task2Handle);
	xTaskCreate(Task3, "Task3", TASK3_STACK, NULL, TASK3_PRIORITY, &Task3Handle);
	//Delete itself.
	vTaskDelete(NULL);
	taskEXIT_CRITICAL();
}

static void Task1(void *pvParameters)
{
	while (1)
	{
		printf("Task1 is running!\n");
		LED_Toggle(LED1);
		vTaskDelay(1000);
	}
}

static void Task2(void *pvParameters)
{
	while (1)
	{
		printf("Task2 is running!\n");
		LED_Toggle(LED2);
		vTaskDelay(1000);
	}
}

static void Task3(void *pvParameters)
{
	while (1)
	{
		printf("Task3 is running!\n");
		uint8_t key = Key_Scan();
		if (key == KEY1)
		{
			if (Task1Handle != NULL)
			{
				vTaskDelete(Task1Handle);
				Task1Handle = NULL;
				LED_Control(LED1, OFF);
			}
		}
		vTaskDelay(1000);
	}
}

void FreeRTOSStart()
{
	//Create a startup task.
	xTaskCreate(StartTask, "StartTask", START_TASK_STACK, NULL, START_TASK_PRIORITY, &startTaskHandle);
	//Start the scheduler.
	vTaskStartScheduler();
}
