#include "stm32f10x.h"                  // Device header
#ifndef __PWM_H
#define __PWM_H
void PWMInit();
void PWMSetCompare1(uint16_t compare);
void PWMSetPrescaler(uint16_t prescaler);
#endif
