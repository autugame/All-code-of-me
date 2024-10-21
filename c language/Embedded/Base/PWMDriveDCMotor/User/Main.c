#include "stm32f10x.h"                  // Device header
#include "Delay.h"
#include "OLED.h"
#include "Motor.h"
#include "Key.h"

int main(void)
{
	uint8_t keyNum = 0;
	int8_t speed = 0;
	OLED_Init();
	MotorInit();

	OLED_ShowString(1, 1, "Speed:");

	while(1)
	{
		keyNum = Key_GetNum();
		if (keyNum == 1)
		{
			speed += 20;
			if (speed > 100)
			{
				speed = -100;
			}
		}
		MotorSetSpeed(speed);
		OLED_ShowSignedNum(1, 7, speed, 3);
	}
}
