#include "stm32f10x.h"                  // Device header
#ifndef __LIGHTSENSOR_H
#define __LIGHTSENSOR_H
void LightSensor_Init(void);
uint8_t LightSensor_Get(void);

#endif
