#include "stm32f10x.h"                  // Device header
#include "Delay.h"
#include "OLED.h"
#include "PWM.h"

int main(void)
{
	OLED_Init();
	PWMInit();
	
	PWMSetCompare2(1500);

	while(1)
	{
		for (uint8_t i = 0; i <= 100; i++) {
			PWMSetCompare1(i);
			Delay_ms(10);
		}
		for (uint8_t i = 0; i <= 100; i++) {
			PWMSetCompare1(100 - i);
			Delay_ms(10);
		}
	}
}
