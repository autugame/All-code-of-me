#include "LED.h"
#include "Delay.h"
#include "OLED.h"
#include "Encoder.h"

int16_t Num;
int main(void)
{
	LED_Init();
	OLED_Init();
	Encoder_Init();
	OLED_ShowString(1, 1, "Num:");

	while(1)
	{
		LED_ON();
		Delay_ms(500);
		LED_OFF();
		Delay_ms(500);
		Num += Encoder_Get();
		OLED_ShowSignedNum(1, 5, Num, 5);
	}
}
