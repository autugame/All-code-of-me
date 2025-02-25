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
#include "queue.h"

#define START_TASK_STACK 128
#define START_TASK_PRIORITY 1
TaskHandle_t startTaskHandle;
static void StartTask(void *pvParameters);

#define TASK1_STACK 256
#define TASK1_PRIORITY 1
TaskHandle_t Task1Handle;
static void Task1(void *pvParameters);

#define TASK2_STACK 256
#define TASK2_PRIORITY 1
TaskHandle_t Task2Handle;
static void Task2(void *pvParameters);

#define TASK3_STACK 256
#define TASK3_PRIORITY 1
TaskHandle_t Task3Handle;
static void Task3(void *pvParameters);

QueueHandle_t queue1;
QueueHandle_t queue2;

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
	uint8_t key = 0;
	char *data = "hhhhhhhhhhhhhhheeeeeeeeeeeeeelllllllllllllllllllllllloooooooooooo!";
	while (1)
	{
		key = Key_Scan();
		if (key == KEY1)
		{
			printf("Key 1\r\n");
			xQueueSend(queue1, &key, portMAX_DELAY);
		}
		else if (key == KEY2)
		{
			printf("Key 2\r\n");
			xQueueSend(queue2, &data, portMAX_DELAY);
		}
		vTaskDelay(1000);
	}
}

static void Task2(void *pvParameters)
{
	uint8_t receive = 0;
	while (1)
	{
		xQueueReceive(queue1, &receive, portMAX_DELAY);
		printf("%d\r\n", receive);
	}
}

static void Task3(void *pvParameters)
{
	char *receiveBuff = 0;
	while (1)
	{
		xQueueReceive(queue2, &receiveBuff, portMAX_DELAY);
		printf("%s\r\n", receiveBuff);
	}
}

void FreeRTOSStart()
{
	//Create queue.
	queue1 = xQueueCreate(2, sizeof(uint8_t));
	queue2 = xQueueCreate(1, sizeof(char *));
	//Create a startup task.
	xTaskCreate(StartTask, "StartTask", START_TASK_STACK, NULL, START_TASK_PRIORITY, &startTaskHandle);
	//Start the scheduler.
	vTaskStartScheduler();
}
