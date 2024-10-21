#include "Servo.h"

void ServoInit()
{
    PWMInit();
}

void ServoSetAngle(float angle)
{
    PWMSetCompare2(angle / 180 * 2000 + 500);
}
