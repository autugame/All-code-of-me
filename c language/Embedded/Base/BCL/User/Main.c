#include "LED.h"
#include "Key.h"

uint8_t keyNum;
int main(void)
{
	LED_Init();
	Key_Init();

	while(1)
	{
		
		keyNum = Key_GetNum();
		if (keyNum)
		{
			LED_Turn();
		}
		// LED_ON();
		// Delay_ms(500);
		// LED_OFF();
		// Delay_ms(500);
	}
}
