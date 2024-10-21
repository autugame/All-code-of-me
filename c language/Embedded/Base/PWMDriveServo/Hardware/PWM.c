#include "PWM.h"

void PWMInit()
{
    RCC_APB1PeriphClockCmd(RCC_APB1Periph_TIM2, ENABLE);

    RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOA, ENABLE);
    GPIO_InitTypeDef GPIO_InitStructure;
    GPIO_InitStructure.GPIO_Mode = GPIO_Mode_AF_PP;
	GPIO_InitStructure.GPIO_Pin = GPIO_Pin_0 | GPIO_Pin_1;
	GPIO_InitStructure.GPIO_Speed = GPIO_Speed_50MHz;
    GPIO_Init(GPIOA, &GPIO_InitStructure);

    TIM_InternalClockConfig(TIM2);

    TIM_TimeBaseInitTypeDef TIMTimeBaseInitStructure;
    TIMTimeBaseInitStructure.TIM_ClockDivision = TIM_CKD_DIV1;
    TIMTimeBaseInitStructure.TIM_CounterMode = TIM_CounterMode_Up;
    TIMTimeBaseInitStructure.TIM_Period = 20000 - 1;
    TIMTimeBaseInitStructure.TIM_Prescaler = 72 - 1;
    TIMTimeBaseInitStructure.TIM_RepetitionCounter = 0;
    TIM_TimeBaseInit(TIM2, &TIMTimeBaseInitStructure);

    TIM_OCInitTypeDef TIMOCInitStructure;
    TIM_OCStructInit(&TIMOCInitStructure);
    TIMOCInitStructure.TIM_OCMode = TIM_OCMode_PWM1;
    TIMOCInitStructure.TIM_OCPolarity = TIM_OCPolarity_High;
    TIMOCInitStructure.TIM_OutputState = TIM_OutputState_Enable;
    TIMOCInitStructure.TIM_Pulse = 0;

    TIM_OC1Init(TIM2, &TIMOCInitStructure);
    TIM_OC2Init(TIM2, &TIMOCInitStructure);

    TIM_Cmd(TIM2, ENABLE);
}

void PWMSetCompare1(uint16_t compare)
{
    TIM_SetCompare1(TIM2, compare);
}

void PWMSetCompare2(uint16_t compare)
{
    TIM_SetCompare2(TIM2, compare);
}
