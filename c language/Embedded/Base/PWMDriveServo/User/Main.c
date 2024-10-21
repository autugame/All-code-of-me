#include "stm32f10x.h"                  // Device header
#include "Delay.h"
#include "OLED.h"
#include "Servo.h"
#include "Key.h"

int main(void)
{
	uint8_t keyNum = 0;
	float angle = 0;
	OLED_Init();
	ServoInit();
	Key_Init();

	OLED_ShowString(1, 1, "Angle:");

	while(1)
	{
		keyNum = Key_GetNum();
		if (keyNum == 1)
		{
			angle += 30;
			if (angle > 180)
			{
				angle = 0;
			}
		}
		ServoSetAngle(angle);
		OLED_ShowNum(1, 7, angle, 3);
	}
}
