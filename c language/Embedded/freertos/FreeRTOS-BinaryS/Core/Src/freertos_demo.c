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
#include "semphr.h"

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

QueueHandle_t Semaphore;

static void StartTask(void *pvParameters)
{
	taskENTER_CRITICAL();
	//Create three tasks.
	xTaskCreate(Task1, "Task1", TASK1_STACK, NULL, TASK1_PRIORITY, &Task1Handle);
	xTaskCreate(Task2, "Task2", TASK2_STACK, NULL, TASK2_PRIORITY, &Task2Handle);
	//Delete itself.
	vTaskDelete(NULL);
	taskEXIT_CRITICAL();
}

static void Task1(void *pvParameters)
{
	uint8_t key = 0;
	while (1)
	{
		key = Key_Scan();
		if (key == KEY1)
		{
			xSemaphoreGive(Semaphore);
		}
		vTaskDelay(1000);
	}
}

static void Task2(void *pvParameters)
{
	while (1)
	{
		xSemaphoreTake(Semaphore, HAL_MAX_DELAY);
		printf("ok\r\n");
	}
}

void FreeRTOSStart()
{
	vSemaphoreCreateBinary(Semaphore);
	//Create a startup task.
	xTaskCreate(StartTask, "StartTask", START_TASK_STACK, NULL, START_TASK_PRIORITY, &startTaskHandle);
	//Start the scheduler.
	vTaskStartScheduler();
}
