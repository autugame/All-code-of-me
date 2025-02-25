/*
 * oled.h
 *
 *  Created on: Feb 19, 2025
 *      Author: A3452
 */

#ifndef INC_OLED_H_
#define INC_OLED_H_

#include "i2c.h"

extern I2C_HandleTypeDef hi2c1;

#define OLED_DEV_ADDRESS 0x78
#define OLED_CMD_ADDRESS 0X00
#define OLED_DATA_ADDRESS 0X40

enum TYPE
{
	VIII_XVI_DOT_MATRIX,
	VI_VIII_DOT_MATRIX
};

//Public functions.
void OLED_Init();
void OLED_Clear();
//Any existing characters can be displayed with this.
void OLED_ShowChar(uint8_t x, uint8_t page, char character, enum TYPE type);
//Display Chinese.
void OLED_ShowCN16X16(uint8_t x, uint8_t page, uint8_t CNIndex);
//Display a string.
void OLED_ShowString(uint8_t x, uint8_t page, char* string, enum TYPE type);
//Display a image.
void OLED_ShowIMG(uint8_t x, uint8_t page, uint8_t imgWeight, uint8_t imgHeight, const uint8_t bmp[]);
//Easier to display numbers.
void OLED_ShowNUM(uint8_t x, uint8_t page, uint32_t num, uint8_t length, enum TYPE type);
void OLED_Test();


#endif /* INC_OLED_H_ */
