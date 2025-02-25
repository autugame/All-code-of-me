################################################################################
# Automatically-generated file. Do not edit!
# Toolchain: GNU Tools for STM32 (12.3.rel1)
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../Middle/FreeRTOS/Portable/MemMang/heap_4.c 

OBJS += \
./Middle/FreeRTOS/Portable/MemMang/heap_4.o 

C_DEPS += \
./Middle/FreeRTOS/Portable/MemMang/heap_4.d 


# Each subdirectory must supply rules for building sources it contributes
Middle/FreeRTOS/Portable/MemMang/%.o Middle/FreeRTOS/Portable/MemMang/%.su Middle/FreeRTOS/Portable/MemMang/%.cyclo: ../Middle/FreeRTOS/Portable/MemMang/%.c Middle/FreeRTOS/Portable/MemMang/subdir.mk
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -g3 -DDEBUG -DUSE_HAL_DRIVER -DSTM32F103xB -c -I../Core/Inc -I../Drivers/STM32F1xx_HAL_Driver/Inc/Legacy -I../Drivers/STM32F1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32F1xx/Include -I../Drivers/CMSIS/Include -I../Middle/FreeRTOS/Portable/include -O0 -ffunction-sections -fdata-sections -Wall -fstack-usage -fcyclomatic-complexity -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"

clean: clean-Middle-2f-FreeRTOS-2f-Portable-2f-MemMang

clean-Middle-2f-FreeRTOS-2f-Portable-2f-MemMang:
	-$(RM) ./Middle/FreeRTOS/Portable/MemMang/heap_4.cyclo ./Middle/FreeRTOS/Portable/MemMang/heap_4.d ./Middle/FreeRTOS/Portable/MemMang/heap_4.o ./Middle/FreeRTOS/Portable/MemMang/heap_4.su

.PHONY: clean-Middle-2f-FreeRTOS-2f-Portable-2f-MemMang

