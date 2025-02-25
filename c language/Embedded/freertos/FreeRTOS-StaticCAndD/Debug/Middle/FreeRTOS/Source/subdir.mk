################################################################################
# Automatically-generated file. Do not edit!
# Toolchain: GNU Tools for STM32 (12.3.rel1)
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../Middle/FreeRTOS/Source/croutine.c \
../Middle/FreeRTOS/Source/event_groups.c \
../Middle/FreeRTOS/Source/list.c \
../Middle/FreeRTOS/Source/queue.c \
../Middle/FreeRTOS/Source/stream_buffer.c \
../Middle/FreeRTOS/Source/tasks.c \
../Middle/FreeRTOS/Source/timers.c 

OBJS += \
./Middle/FreeRTOS/Source/croutine.o \
./Middle/FreeRTOS/Source/event_groups.o \
./Middle/FreeRTOS/Source/list.o \
./Middle/FreeRTOS/Source/queue.o \
./Middle/FreeRTOS/Source/stream_buffer.o \
./Middle/FreeRTOS/Source/tasks.o \
./Middle/FreeRTOS/Source/timers.o 

C_DEPS += \
./Middle/FreeRTOS/Source/croutine.d \
./Middle/FreeRTOS/Source/event_groups.d \
./Middle/FreeRTOS/Source/list.d \
./Middle/FreeRTOS/Source/queue.d \
./Middle/FreeRTOS/Source/stream_buffer.d \
./Middle/FreeRTOS/Source/tasks.d \
./Middle/FreeRTOS/Source/timers.d 


# Each subdirectory must supply rules for building sources it contributes
Middle/FreeRTOS/Source/%.o Middle/FreeRTOS/Source/%.su Middle/FreeRTOS/Source/%.cyclo: ../Middle/FreeRTOS/Source/%.c Middle/FreeRTOS/Source/subdir.mk
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -g3 -DDEBUG -DUSE_HAL_DRIVER -DSTM32F103xB -c -I../Core/Inc -I../Drivers/STM32F1xx_HAL_Driver/Inc/Legacy -I../Drivers/STM32F1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32F1xx/Include -I../Drivers/CMSIS/Include -I../Middle/FreeRTOS/Portable/include -O0 -ffunction-sections -fdata-sections -Wall -fstack-usage -fcyclomatic-complexity -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"

clean: clean-Middle-2f-FreeRTOS-2f-Source

clean-Middle-2f-FreeRTOS-2f-Source:
	-$(RM) ./Middle/FreeRTOS/Source/croutine.cyclo ./Middle/FreeRTOS/Source/croutine.d ./Middle/FreeRTOS/Source/croutine.o ./Middle/FreeRTOS/Source/croutine.su ./Middle/FreeRTOS/Source/event_groups.cyclo ./Middle/FreeRTOS/Source/event_groups.d ./Middle/FreeRTOS/Source/event_groups.o ./Middle/FreeRTOS/Source/event_groups.su ./Middle/FreeRTOS/Source/list.cyclo ./Middle/FreeRTOS/Source/list.d ./Middle/FreeRTOS/Source/list.o ./Middle/FreeRTOS/Source/list.su ./Middle/FreeRTOS/Source/queue.cyclo ./Middle/FreeRTOS/Source/queue.d ./Middle/FreeRTOS/Source/queue.o ./Middle/FreeRTOS/Source/queue.su ./Middle/FreeRTOS/Source/stream_buffer.cyclo ./Middle/FreeRTOS/Source/stream_buffer.d ./Middle/FreeRTOS/Source/stream_buffer.o ./Middle/FreeRTOS/Source/stream_buffer.su ./Middle/FreeRTOS/Source/tasks.cyclo ./Middle/FreeRTOS/Source/tasks.d ./Middle/FreeRTOS/Source/tasks.o ./Middle/FreeRTOS/Source/tasks.su ./Middle/FreeRTOS/Source/timers.cyclo ./Middle/FreeRTOS/Source/timers.d ./Middle/FreeRTOS/Source/timers.o ./Middle/FreeRTOS/Source/timers.su

.PHONY: clean-Middle-2f-FreeRTOS-2f-Source

