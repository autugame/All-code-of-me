#include "freertos_dynamic_tasks_demo.h"
#include "FreeRTOS.h"
#include "task.h"
#include "main.h"

#define stack_depth 128
#define priority 1

TaskHandle_t start_task_handle;
TaskHandle_t start_task1_handle;

void start_task(const void *pv_parameters);
void start_task1(const void *pv_parameters);

void freertos_dynamic_tasks_init() {
	//1.Create a startup task.
	xTaskCreate((TaskFunction_t) start_task, "start_task", stack_depth, NULL, (UBaseType_t) priority, &start_task_handle);
	//2.Start the scheduler.
	vTaskStartScheduler();
}

void start_task(const void *pv_parameters) {
	taskENTER_CRITICAL();
	xTaskCreate((TaskFunction_t) start_task1, "start_task1", stack_depth, NULL, (UBaseType_t) priority, &start_task1_handle);
	//Delete the init task.
	vTaskDelete(NULL);
	taskEXIT_CRITICAL();
}

void start_task1(const void *pv_parameters) {
	while (1) {
		HAL_GPIO_WritePin(GPIOC, GPIO_PIN_13, GPIO_PIN_RESET);
		vTaskDelay(500);
		HAL_GPIO_WritePin(GPIOC, GPIO_PIN_13, GPIO_PIN_SET);
		vTaskDelay(500);
	}
}
