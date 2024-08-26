#include "LED.h"
#include "Delay.h"
#include "OLED.h"
#include "CountSensor.h"

uint8_t keyNum;
int main(void)
{
	LED_Init();
	OLED_Init();
	CountSensor_Init();

	OLED_ShowString(1, 1, "Count:");
	

	while(1)
	{
		LED_ON();
		Delay_ms(500);
		LED_OFF();
		Delay_ms(500);
		OLED_ShowNum(1, 7, CountSensor_Get(), 5);
	}
}
