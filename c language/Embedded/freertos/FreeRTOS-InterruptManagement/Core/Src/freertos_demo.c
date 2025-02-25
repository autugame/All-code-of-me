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

static void StartTask(void *pvParameters)
{
	taskENTER_CRITICAL();
	//Create three tasks.
	xTaskCreate(Task1, "Task1", TASK1_STACK, NULL, TASK1_PRIORITY, &Task1Handle);
	//Delete itself.
	vTaskDelete(NULL);
	taskEXIT_CRITICAL();
}

char taskInfo[500];
static void Task1(void *pvParameters)
{
	while (1)
	{
		printf("Task1 is running!\n");
		LED_Toggle(LED1);
		vTaskDelay(1000);
		//Print task status.
		vTaskList(taskInfo);
		printf("%s\n", taskInfo);
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
