################################################################################
# Automatically-generated file. Do not edit!
# Toolchain: GNU Tools for STM32 (12.3.rel1)
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../freertos/source/croutine.c \
../freertos/source/event_groups.c \
../freertos/source/list.c \
../freertos/source/queue.c \
../freertos/source/stream_buffer.c \
../freertos/source/tasks.c \
../freertos/source/timers.c 

OBJS += \
./freertos/source/croutine.o \
./freertos/source/event_groups.o \
./freertos/source/list.o \
./freertos/source/queue.o \
./freertos/source/stream_buffer.o \
./freertos/source/tasks.o \
./freertos/source/timers.o 

C_DEPS += \
./freertos/source/croutine.d \
./freertos/source/event_groups.d \
./freertos/source/list.d \
./freertos/source/queue.d \
./freertos/source/stream_buffer.d \
./freertos/source/tasks.d \
./freertos/source/timers.d 


# Each subdirectory must supply rules for building sources it contributes
freertos/source/%.o freertos/source/%.su freertos/source/%.cyclo: ../freertos/source/%.c freertos/source/subdir.mk
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -g3 -DDEBUG -DUSE_HAL_DRIVER -DSTM32F103xB -c -I../Core/Inc -I../Drivers/STM32F1xx_HAL_Driver/Inc/Legacy -I../Drivers/STM32F1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32F1xx/Include -I../Drivers/CMSIS/Include -I../freertos -I../freertos/include -I../freertos/portable/ARM_CM3/inc -O0 -ffunction-sections -fdata-sections -Wall -fstack-usage -fcyclomatic-complexity -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"

clean: clean-freertos-2f-source

clean-freertos-2f-source:
	-$(RM) ./freertos/source/croutine.cyclo ./freertos/source/croutine.d ./freertos/source/croutine.o ./freertos/source/croutine.su ./freertos/source/event_groups.cyclo ./freertos/source/event_groups.d ./freertos/source/event_groups.o ./freertos/source/event_groups.su ./freertos/source/list.cyclo ./freertos/source/list.d ./freertos/source/list.o ./freertos/source/list.su ./freertos/source/queue.cyclo ./freertos/source/queue.d ./freertos/source/queue.o ./freertos/source/queue.su ./freertos/source/stream_buffer.cyclo ./freertos/source/stream_buffer.d ./freertos/source/stream_buffer.o ./freertos/source/stream_buffer.su ./freertos/source/tasks.cyclo ./freertos/source/tasks.d ./freertos/source/tasks.o ./freertos/source/tasks.su ./freertos/source/timers.cyclo ./freertos/source/timers.d ./freertos/source/timers.o ./freertos/source/timers.su

.PHONY: clean-freertos-2f-source

