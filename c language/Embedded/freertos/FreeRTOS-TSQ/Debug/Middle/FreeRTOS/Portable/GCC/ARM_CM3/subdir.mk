################################################################################
# Automatically-generated file. Do not edit!
# Toolchain: GNU Tools for STM32 (12.3.rel1)
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../Middle/FreeRTOS/Portable/GCC/ARM_CM3/port.c 

OBJS += \
./Middle/FreeRTOS/Portable/GCC/ARM_CM3/port.o 

C_DEPS += \
./Middle/FreeRTOS/Portable/GCC/ARM_CM3/port.d 


# Each subdirectory must supply rules for building sources it contributes
Middle/FreeRTOS/Portable/GCC/ARM_CM3/%.o Middle/FreeRTOS/Portable/GCC/ARM_CM3/%.su Middle/FreeRTOS/Portable/GCC/ARM_CM3/%.cyclo: ../Middle/FreeRTOS/Portable/GCC/ARM_CM3/%.c Middle/FreeRTOS/Portable/GCC/ARM_CM3/subdir.mk
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -g3 -DDEBUG -DUSE_HAL_DRIVER -DSTM32F103xB -c -I../Core/Inc -I../Drivers/STM32F1xx_HAL_Driver/Inc/Legacy -I../Drivers/STM32F1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32F1xx/Include -I../Drivers/CMSIS/Include -I../Middle/FreeRTOS/Portable/include -O0 -ffunction-sections -fdata-sections -Wall -fstack-usage -fcyclomatic-complexity -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"

clean: clean-Middle-2f-FreeRTOS-2f-Portable-2f-GCC-2f-ARM_CM3

clean-Middle-2f-FreeRTOS-2f-Portable-2f-GCC-2f-ARM_CM3:
	-$(RM) ./Middle/FreeRTOS/Portable/GCC/ARM_CM3/port.cyclo ./Middle/FreeRTOS/Portable/GCC/ARM_CM3/port.d ./Middle/FreeRTOS/Portable/GCC/ARM_CM3/port.o ./Middle/FreeRTOS/Portable/GCC/ARM_CM3/port.su

.PHONY: clean-Middle-2f-FreeRTOS-2f-Portable-2f-GCC-2f-ARM_CM3

