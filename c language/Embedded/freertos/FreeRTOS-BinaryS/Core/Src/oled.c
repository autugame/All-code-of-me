/*
 * oled.c
 *
 *  Created on: Feb 19, 2025
 *      Author: A3452
 */

#include "oled.h"
#include "oled_font.h"

//Private functions.
static void WriteCMD(uint8_t cmd);
static void WriteData(uint8_t data);
static void SetPosition(uint8_t x, uint8_t page);
static void GetNUMPOST(uint32_t num, uint8_t length, uint8_t* array);

//Private functions.
static void WriteCMD(uint8_t cmd)
{
	HAL_I2C_Mem_Write(&hi2c1, OLED_DEV_ADDRESS, OLED_CMD_ADDRESS, I2C_MEMADD_SIZE_8BIT, &cmd, 1, 0x100);
}
static void WriteData(uint8_t data)
{
	HAL_I2C_Mem_Write(&hi2c1, OLED_DEV_ADDRESS, OLED_DATA_ADDRESS, I2C_MEMADD_SIZE_8BIT, &data, 1, 0x100);
}
//Setting the screen pointer.
static void SetPosition(uint8_t x, uint8_t page)
{
	WriteCMD(0x00 | (x & 0x0F));
	WriteCMD(0x10 | (x & 0xF0) >> 4);
	WriteCMD(0xB0 | page);
}
static void GetNUMPOST(uint32_t num, uint8_t length, uint8_t* array)
{
	for (int i = 0; i < length; i++)
	{
		array[length - i - 1] = num % 10;
		num = num / 10;
	}
}

//Public functions.
void OLED_Init()
{
	uint8_t cmdData[]={
			0xAE, 0xD5, 0x80, 0xA8, 0x3F, 0xD3, 0x00, 0x40,0xA1, 0xC8, 0xDA,
			0x12, 0x81, 0xCF, 0xD9, 0xF1, 0xDB, 0x40, 0xA4, 0xA6,0x8D, 0x14,
			0xAF};
	for (uint8_t i = 0; i < 23; i++)
	{
		WriteCMD(cmdData[i]);
	}
	OLED_Clear();
}
//Clear the entire screen.
void OLED_Clear()
{
	for (uint8_t i = 0; i < 128; i++)
	{
		for (uint8_t j = 0; j < 8; j++)
		{
			SetPosition(i, j);
			WriteData(0x00);
		}
	}
}
void OLED_ShowChar(uint8_t x, uint8_t page, char character, enum TYPE type)
{
	uint8_t index = character - ' ';
	switch (type)
	{
		case VI_VIII_DOT_MATRIX :
			SetPosition(x, page);
			for(uint8_t i = 0; i < 6; i++)
			{
				WriteData(F6x8[index][i]);
			}
			break;
		case VIII_XVI_DOT_MATRIX :
			SetPosition(x, page);
			for(uint8_t i = 0; i < 8; i++)
			{
				WriteData(F8X16[index][i]);
			}
			SetPosition(x, page + 1);
			for(uint8_t i = 0; i < 8; i++)
			{
				WriteData(F8X16[index][i + 8]);
			}
			break;
	}
}
void OLED_ShowCN16X16(uint8_t x, uint8_t page, uint8_t CNIndex)
{
	SetPosition(x, page);
	for(uint8_t i = 0; i < 16; i++)
	{
		WriteData(F16x16[CNIndex * 2][i]);
	}
	SetPosition(x, page + 1);
	for(uint8_t i = 0; i < 16; i++)
	{
		WriteData(F16x16[CNIndex * 2 + 1][i]);
	}
}
void OLED_ShowString(uint8_t x, uint8_t page, char* string, enum TYPE type)
{
	switch (type)
	{
		case VI_VIII_DOT_MATRIX :
			for (uint8_t i = 0; string[i] != '\0'; i++)
			{
				OLED_ShowChar(x + i * 6, page, string[i], VI_VIII_DOT_MATRIX);
			}
			break;
		case VIII_XVI_DOT_MATRIX :
			for (uint8_t i = 0; string[i] != '\0'; i++)
			{
				OLED_ShowChar(x + i * 8, page, string[i], VIII_XVI_DOT_MATRIX);
			}
			break;
	}
}
void OLED_ShowIMG(uint8_t x, uint8_t page, uint8_t imgWeight, uint8_t imgHeight, const uint8_t bmp[])
{
	for (uint8_t i = 0; i < imgHeight; i++)
	{
		SetPosition(x, page + i);
		for (uint8_t j = 0; j < imgWeight; j++)
		{
			WriteData(bmp[i * imgWeight + j]);
		}
	}
}
void OLED_ShowNUM(uint8_t x, uint8_t page, uint32_t num, uint8_t length, enum TYPE type)
{
	uint8_t numArray[length];
	GetNUMPOST(num, length, numArray);
	switch (type)
	{
		case VI_VIII_DOT_MATRIX :
			for (uint8_t i = 0; i < length; i++)
			{
				OLED_ShowChar(x + i * 6, page, numArray[i] + '0', VI_VIII_DOT_MATRIX);
			}
			break;
		case VIII_XVI_DOT_MATRIX :
			for (uint8_t i = 0; i < length; i++)
			{
				OLED_ShowChar(x + i * 8, page, numArray[i] + '0', VI_VIII_DOT_MATRIX);
			}
			break;
	}
}

void OLED_Test()
{
	//OLED_ShowString(0, 0, "Hello world!", VI_VIII_DOT_MATRIX);
	//OLED_ShowString(0, 1, "Hello world!", VIII_XVI_DOT_MATRIX);
	//OLED_ShowIMG(0, 0, 48, 6, image);
	OLED_ShowIMG(0, 0, 128, 8, BMP1);
	//OLED_ShowNUM(0, 0, 123, 3, VI_VIII_DOT_MATRIX);
	//OLED_ShowNUM(0, 1, 123, 3, VIII_XVI_DOT_MATRIX);
}
