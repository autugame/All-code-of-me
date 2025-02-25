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

#define TASK2_STACK 256
#define TASK2_PRIORITY 1
TaskHandle_t Task2Handle;
static void Task2(void *pvParameters);

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
		UBaseType_t taskPriority = uxTaskPriorityGet(Task1Handle);
		UBaseType_t taskNum = uxTaskGetNumberOfTasks();
		TaskStatus_t taskStatus[taskNum];
		uxTaskGetSystemState(taskStatus, taskNum, NULL);
		UBaseType_t taskStack = uxTaskGetStackHighWaterMark(Task2Handle);
		printf("The task priority is %lu.\r\n", taskPriority);
		printf("The task taskNum is %lu.\r\n", taskNum);
		printf("Task name\t\tTask ID\t\tCurrent state\t\tCurrent priority\t\tBase priority\r\n");
		for (uint8_t i = 0; i < taskNum; i++)
		{
			printf("%s\t\t%lu\t\t%d\t\t%lu\t\t%lu\r\n", taskStatus[i].pcTaskName, taskStatus[i].xTaskNumber, taskStatus[i].eCurrentState, taskStatus[i].uxCurrentPriority, taskStatus[i].uxBasePriority);
		}
		printf("Minimum remaining task stack is %lu.\r\n", taskStack);
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
