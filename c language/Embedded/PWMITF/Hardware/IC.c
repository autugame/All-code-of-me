#include "IC.h"

void ICInit()
{
    RCC_APB1PeriphClockCmd(RCC_APB1Periph_TIM3, ENABLE);
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOA, ENABLE);

    GPIO_InitTypeDef GPIO_InitStructure;
    GPIO_InitStructure.GPIO_Mode = GPIO_Mode_IPU;
	GPIO_InitStructure.GPIO_Pin = GPIO_Pin_6;
	GPIO_InitStructure.GPIO_Speed = GPIO_Speed_50MHz;
    GPIO_Init(GPIOA, &GPIO_InitStructure);

    TIM_InternalClockConfig(TIM2);

    TIM_TimeBaseInitTypeDef TIMTimeBaseInitStructure;
    TIMTimeBaseInitStructure.TIM_ClockDivision = TIM_CKD_DIV1;
    TIMTimeBaseInitStructure.TIM_CounterMode = TIM_CounterMode_Up;
    TIMTimeBaseInitStructure.TIM_Period = 65536 - 1;
    TIMTimeBaseInitStructure.TIM_Prescaler = 72 - 1;
    TIMTimeBaseInitStructure.TIM_RepetitionCounter = 0;
    TIM_TimeBaseInit(TIM3, &TIMTimeBaseInitStructure);

    TIM_ICInitTypeDef TIMICInitStructure;
    TIMICInitStructure.TIM_Channel = TIM_Channel_1;
    TIMICInitStructure.TIM_ICFilter = 0xF;
    TIMICInitStructure.TIM_ICPolarity = TIM_ICPolarity_Rising;
    TIMICInitStructure.TIM_ICPrescaler = TIM_CKD_DIV1;
    TIMICInitStructure.TIM_ICSelection = TIM_ICSelection_DirectTI;

    TIM_PWMIConfig(TIM3, &TIMICInitStructure);

    TIM_SelectInputTrigger(TIM3, TIM_TS_TI1FP1);
    TIM_SelectSlaveMode(TIM3, TIM_SlaveMode_Reset);

    TIM_Cmd(TIM3, ENABLE);
}

uint32_t ICGetFreq()
{
    return 1000000 / (TIM_GetCapture1(TIM3) + 1);
}

uint32_t ICGetDuty()
{
    return (TIM_GetCapture2(TIM3) + 1) * 100 / (TIM_GetCapture1(TIM3) + 1);
}
