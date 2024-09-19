#include "stm32f10x.h"                  // Device header
#include "PWM.h"
#ifndef __MOTOR_H_
#define __MOTOR_H_
void MotorInit();
void MotorSetSpeed(int8_t speed);
#endif
