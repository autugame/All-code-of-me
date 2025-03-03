#include "PWM.h"

void PWMInit()
{
    RCC_APB1PeriphClockCmd(RCC_APB1Periph_TIM2, ENABLE);

    RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOA, ENABLE);
    GPIO_InitTypeDef GPIO_InitStructure;
    GPIO_InitStructure.GPIO_Mode = GPIO_Mode_AF_PP;
	GPIO_InitStructure.GPIO_Pin = GPIO_Pin_0;
	GPIO_InitStructure.GPIO_Speed = GPIO_Speed_50MHz;
    GPIO_Init(GPIOA, &GPIO_InitStructure);

    TIM_InternalClockConfig(TIM2);

    TIM_TimeBaseInitTypeDef TIMTimeBaseInitStructure;
    TIMTimeBaseInitStructure.TIM_ClockDivision = TIM_CKD_DIV1;
    TIMTimeBaseInitStructure.TIM_CounterMode = TIM_CounterMode_Up;
    TIMTimeBaseInitStructure.TIM_Period = 100 - 1;
    TIMTimeBaseInitStructure.TIM_Prescaler = 720 - 1;
    TIMTimeBaseInitStructure.TIM_RepetitionCounter = 0;
    TIM_TimeBaseInit(TIM2, &TIMTimeBaseInitStructure);

    TIM_OCInitTypeDef TIMOCInitStructure;
    TIM_OCStructInit(&TIMOCInitStructure);
    TIMOCInitStructure.TIM_OCMode = TIM_OCMode_PWM1;
    TIMOCInitStructure.TIM_OCPolarity = TIM_OCPolarity_High;
    TIMOCInitStructure.TIM_OutputState = TIM_OutputState_Enable;
    TIMOCInitStructure.TIM_Pulse = 0;

    TIM_OC1Init(TIM2, &TIMOCInitStructure);

    TIM_Cmd(TIM2, ENABLE);
}

void PWMSetCompare1(uint16_t compare)
{
    TIM_SetCompare1(TIM2, compare);
}

void PWMSetPrescaler(uint16_t prescaler)
{
    TIM_PrescalerConfig(TIM2, prescaler, TIM_PSCReloadMode_Update);
}
