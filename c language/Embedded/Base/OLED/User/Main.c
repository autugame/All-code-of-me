#include "LED.h"
#include "Delay.h"
#include "OLED.h"

int main(void)
{
	LED_Init();
	OLED_Init();

	OLED_ShowChar(1, 1, 'A');
	OLED_ShowString(1, 3, "HelloWorld!");
	OLED_ShowNum(2, 1, 12345, 5);
	OLED_ShowSignedNum(2, 7, 12345, 5);
	OLED_ShowHexNum(3, 1, 0x1101, 4);
	OLED_ShowString(3, 4, "password");
	OLED_ShowBinNum(4, 1, 0x000F, 16);

	while(1)
	{
		LED_ON();
		Delay_ms(500);
		LED_OFF();
		Delay_ms(500);
	}
}
