#include "stm32f10x.h"                  // Device header
#include "PWM.h"
#ifndef __SERVO_H_
#define __SERVO_H_
void ServoInit();
void ServoSetAngle(float angle);
#endif
