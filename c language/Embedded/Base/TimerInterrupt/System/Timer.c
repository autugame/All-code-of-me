#include "Timer.h"

void TimerInit()
{
    RCC_APB1PeriphClockCmd(RCC_APB1Periph_TIM2, ENABLE);

    TIM_InternalClockConfig(TIM2);

    TIM_TimeBaseInitTypeDef TIMTimeBaseInitStructure;
    TIMTimeBaseInitStructure.TIM_ClockDivision = TIM_CKD_DIV1;
    TIMTimeBaseInitStructure.TIM_CounterMode = TIM_CounterMode_Up;
    TIMTimeBaseInitStructure.TIM_Period = 10000 - 1;
    TIMTimeBaseInitStructure.TIM_Prescaler = 7200 - 1;
    TIMTimeBaseInitStructure.TIM_RepetitionCounter = 0;
    TIM_TimeBaseInit(TIM2, &TIMTimeBaseInitStructure);

    TIM_ClearFlag(TIM2, TIM_FLAG_Update);

    TIM_ITConfig(TIM2, TIM_IT_Update, ENABLE);

    NVIC_PriorityGroupConfig(NVIC_PriorityGroup_2);

    NVIC_InitTypeDef NVICInitStructure;
    NVICInitStructure.NVIC_IRQChannel = TIM2_IRQn;
    NVICInitStructure.NVIC_IRQChannelCmd = ENABLE;
    NVICInitStructure.NVIC_IRQChannelPreemptionPriority = 2;
    NVICInitStructure.NVIC_IRQChannelSubPriority = 1;
    
    NVIC_Init(&NVICInitStructure);

    TIM_Cmd(TIM2, ENABLE);
}
