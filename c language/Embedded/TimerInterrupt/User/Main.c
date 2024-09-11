#include "LED.h"
#include "Delay.h"
#include "OLED.h"
#include "Timer.h"

uint16_t num = 0;
int main(void)
{
	OLED_Init();
	TimerInit();

	OLED_ShowString(1, 1, "NUM:");
	

	while(1)
	{
		OLED_ShowNum(1, 5, num, 5);
	}
}

void TIM2_IRQHandler()
{
    if (TIM_GetITStatus(TIM2, TIM_IT_Update) == SET)
    {
        num++;
        TIM_ClearITPendingBit(TIM2, TIM_IT_Update);
    }
}
