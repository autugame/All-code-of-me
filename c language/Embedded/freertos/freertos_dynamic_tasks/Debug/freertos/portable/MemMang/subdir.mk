################################################################################
# Automatically-generated file. Do not edit!
# Toolchain: GNU Tools for STM32 (12.3.rel1)
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../freertos/portable/MemMang/heap_4.c 

OBJS += \
./freertos/portable/MemMang/heap_4.o 

C_DEPS += \
./freertos/portable/MemMang/heap_4.d 


# Each subdirectory must supply rules for building sources it contributes
freertos/portable/MemMang/%.o freertos/portable/MemMang/%.su freertos/portable/MemMang/%.cyclo: ../freertos/portable/MemMang/%.c freertos/portable/MemMang/subdir.mk
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -g3 -DDEBUG -DUSE_HAL_DRIVER -DSTM32F103xB -c -I../Core/Inc -I../Drivers/STM32F1xx_HAL_Driver/Inc/Legacy -I../Drivers/STM32F1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32F1xx/Include -I../Drivers/CMSIS/Include -I../freertos -I../freertos/include -I../freertos/portable/ARM_CM3/inc -O0 -ffunction-sections -fdata-sections -Wall -fstack-usage -fcyclomatic-complexity -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"

clean: clean-freertos-2f-portable-2f-MemMang

clean-freertos-2f-portable-2f-MemMang:
	-$(RM) ./freertos/portable/MemMang/heap_4.cyclo ./freertos/portable/MemMang/heap_4.d ./freertos/portable/MemMang/heap_4.o ./freertos/portable/MemMang/heap_4.su

.PHONY: clean-freertos-2f-portable-2f-MemMang

