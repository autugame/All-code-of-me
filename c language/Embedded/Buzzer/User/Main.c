#include "stm32f10x.h"                  // Device header
#include "Delay.h"

int main(void)
{
	GPIO_InitTypeDef GPIOA_InitStructure;
	GPIO_InitTypeDef GPIOB_InitStructure;
	RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOA | RCC_APB2Periph_GPIOB, ENABLE);
	GPIOA_InitStructure.GPIO_Mode = GPIO_Mode_Out_PP;
	GPIOA_InitStructure.GPIO_Pin = GPIO_Pin_0;
	GPIOA_InitStructure.GPIO_Speed = GPIO_Speed_50MHz;
	GPIOB_InitStructure.GPIO_Mode = GPIO_Mode_Out_PP;
	GPIOB_InitStructure.GPIO_Pin = GPIO_Pin_12;
	GPIOB_InitStructure.GPIO_Speed = GPIO_Speed_50MHz;
	GPIO_Init(GPIOA, &GPIOA_InitStructure);
	GPIO_Init(GPIOB, &GPIOB_InitStructure);

	while(1)
	{
		GPIO_SetBits(GPIOA, GPIO_Pin_0);
		Delay_ms(500);
		GPIO_ResetBits(GPIOA, GPIO_Pin_0);
		GPIO_SetBits(GPIOB, GPIO_Pin_12);
		Delay_ms(500);
		GPIO_ResetBits(GPIOB, GPIO_Pin_12);
	}
}
