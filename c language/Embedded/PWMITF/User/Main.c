#include "stm32f10x.h"                  // Device header
#include "Delay.h"
#include "OLED.h"
#include "PWM.h"
#include "IC.h"

int main(void)
{
	OLED_Init();
	PWMInit();
	ICInit();

	OLED_ShowString(1, 1, "Freq:00000HZ");
	OLED_ShowString(2, 1, "Duty:00%");

	PWMSetPrescaler(7200 - 1);
	PWMSetCompare1(50);

	while (1)
	{
		OLED_ShowNum(1, 6, ICGetFreq(), 5);
		OLED_ShowNum(2, 6, ICGetDuty(), 2);
	}
}
