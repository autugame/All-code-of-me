#include "LED.h"
#include "Key.h"
#include "Delay.h"
#include "Buzzer.h"
#include "LightSensor.h"

uint8_t keyNum;
int main(void)
{
	LED_Init();
	Buzzer_Init();
	LightSensor_Init();

	while(1)
	{
		if (LightSensor_Get())
		{
			Buzzer_OFF();
		}
		else
		{
			Buzzer_ON();
		}
		LED_ON();
		Delay_ms(500);
		LED_OFF();
		Delay_ms(500);
	}
}
