#include "stm32f10x.h"                  // Device header
#ifndef __PWM_H_
#define __PWM_H_
void PWMInit();
void PWMSetCompare1(uint16_t compare);
void PWMSetCompare2(uint16_t compare);
#endif