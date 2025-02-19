#include "w25qxx.h"

static void W25Qx_Reset(void);
static uint8_t W25Qx_GetStatus(void);
/*
 * w25qxx.c
 *
 *  Created on: Feb 17, 2025
 *      Author: A3452
 */


 /**********************************************************************************
  * 函数功能: 模块初始化
  */
uint8_t W25Qx_Init(void)
{
	W25Qx_Reset();
	return W25Qx_GetStatus();
}


static void W25Qx_Reset(void)
{
	uint8_t cmd[2] = {RESET_ENABLE_CMD,RESET_MEMORY_CMD};

	W25Qx_Enable();
	/* Send the reset command */
	HAL_SPI_Transmit(W25QX_SPI_Handler, cmd, 2, W25Qx_TIMEOUT_VALUE);

	W25Qx_Disable();

}

 /**********************************************************************************
  * 函数功能: 获取设备状态
  */
static uint8_t W25Qx_GetStatus(void)
{
	uint8_t cmd[] = {READ_STATUS_REG1_CMD};
	uint8_t status;

	W25Qx_Enable();

	HAL_SPI_Transmit(W25QX_SPI_Handler, cmd, 1, W25Qx_TIMEOUT_VALUE);
	HAL_SPI_Receive(W25QX_SPI_Handler,&status, 1, W25Qx_TIMEOUT_VALUE);


	W25Qx_Disable();

	/* Check the value of the register */
  if((status & W25Q128FV_FSR_BUSY) != 0)
  {
    return W25Qx_BUSY;
  }
	else
	{
		return W25Qx_OK;
	}
}

 /**********************************************************************************
  * 函数功能: 写使能
  */
uint8_t W25Qx_WriteEnable(void)
{
	uint8_t cmd[] = {WRITE_ENABLE_CMD};
	uint32_t tickstart = HAL_GetTick();

	/*Select the FLASH: Chip Select low */
	W25Qx_Enable();
	/* Send the read ID command */
	HAL_SPI_Transmit(W25QX_SPI_Handler, cmd, 1, W25Qx_TIMEOUT_VALUE);

	/*Deselect the FLASH: Chip Select high */
	W25Qx_Disable();

	/* Wait the end of Flash writing */
	while(W25Qx_GetStatus() == W25Qx_BUSY)
	{
		/* Check for the Timeout */
    if((HAL_GetTick() - tickstart) > W25Qx_TIMEOUT_VALUE)
    {
			return W25Qx_TIMEOUT;
    }
	}

	return W25Qx_OK;
}

 /**********************************************************************************
  * 函数功能: 获取设备ID
  */
void W25Qx_Read_ID(uint8_t *ID)
{
	uint8_t cmd[4] = {READ_ID_CMD,0x00,0x00,0x00};

	W25Qx_Enable();

	HAL_SPI_Transmit(W25QX_SPI_Handler, cmd, 4, W25Qx_TIMEOUT_VALUE);
	HAL_SPI_Receive(W25QX_SPI_Handler,ID, 2, W25Qx_TIMEOUT_VALUE);

	W25Qx_Disable();

}

 /**********************************************************************************
  * 函数功能: 读数据
  * 输入参数: 缓存数组指针、读地址、字节数
  */
uint8_t W25Qx_Read(uint8_t* pData, uint32_t ReadAddr, uint32_t Size)
{
	uint8_t cmd[4];

	/* Configure the command */
	cmd[0] = READ_CMD;
	cmd[1] = (uint8_t)(ReadAddr >> 16);
	cmd[2] = (uint8_t)(ReadAddr >> 8);
	cmd[3] = (uint8_t)(ReadAddr);

	W25Qx_Enable();

	/* Send the read ID command */
	HAL_SPI_Transmit(W25QX_SPI_Handler, cmd, 4, W25Qx_TIMEOUT_VALUE);


	/* Reception of the data */
	if (HAL_SPI_Receive(W25QX_SPI_Handler, pData,Size,W25Qx_TIMEOUT_VALUE) != HAL_OK)

  {
    return W25Qx_ERROR;
  }
	W25Qx_Disable();
	return W25Qx_OK;
}




 /**********************************************************************************
  * 函数功能: 写数据
  * 输入参数: 缓存数组指针、写地址、字节数
  */
uint8_t W25Qx_Write(uint8_t* pData, uint32_t WriteAddr, uint32_t Size)
{
	uint8_t cmd[4];
	uint32_t end_addr, current_size, current_addr;
	uint32_t tickstart = HAL_GetTick();

	/* Calculation of the size between the write address and the end of the page */
  current_addr = 0;

  while (current_addr <= WriteAddr)
  {
    current_addr += W25Q128FV_PAGE_SIZE;
  }
  current_size = current_addr - WriteAddr;

  /* Check if the size of the data is less than the remaining place in the page */
  if (current_size > Size)
  {
    current_size = Size;
  }

  /* Initialize the adress variables */
  current_addr = WriteAddr;
  end_addr = WriteAddr + Size;

  /* Perform the write page by page */
  do
  {
		/* Configure the command */
		cmd[0] = PAGE_PROG_CMD;
		cmd[1] = (uint8_t)(current_addr >> 16);
		cmd[2] = (uint8_t)(current_addr >> 8);
		cmd[3] = (uint8_t)(current_addr);

		/* Enable write operations */
		W25Qx_WriteEnable();

		W25Qx_Enable();


		 /* Send the command */
    if (HAL_SPI_Transmit(W25QX_SPI_Handler,cmd, 4, W25Qx_TIMEOUT_VALUE) != HAL_OK)
    {
      return W25Qx_ERROR;
    }


		/* Transmission of the data */
		if (HAL_SPI_Transmit(W25QX_SPI_Handler, pData,current_size, W25Qx_TIMEOUT_VALUE) != HAL_OK)
    {
      return W25Qx_ERROR;
    }
			W25Qx_Disable();
    	/* Wait the end of Flash writing */
		while(W25Qx_GetStatus() == W25Qx_BUSY)
		{
			/* Check for the Timeout */
			if((HAL_GetTick() - tickstart) > W25Qx_TIMEOUT_VALUE)
			{
				return W25Qx_TIMEOUT;
			}
		}

    /* Update the address and size variables for next page programming */
    current_addr += current_size;
    pData += current_size;
    current_size = ((current_addr + W25Q128FV_PAGE_SIZE) > end_addr) ? (end_addr - current_addr) : W25Q128FV_PAGE_SIZE;
  } while (current_addr < end_addr);


	return W25Qx_OK;
}

 /**********************************************************************************
  * 函数功能: 块擦除
  * 输入参数: 地址
  */
uint8_t W25Qx_Erase_Block(uint32_t Address)
{
	uint8_t cmd[4];
	uint32_t tickstart = HAL_GetTick();
	cmd[0] = SECTOR_ERASE_CMD;
	cmd[1] = (uint8_t)(Address >> 16);
	cmd[2] = (uint8_t)(Address >> 8);
	cmd[3] = (uint8_t)(Address);

	/* Enable write operations */
	W25Qx_WriteEnable();

	/*Select the FLASH: Chip Select low */
	W25Qx_Enable();


	/* Send the SECTOR_ERASE command */
	HAL_SPI_Transmit(W25QX_SPI_Handler, cmd, 4, W25Qx_TIMEOUT_VALUE);


	/*Deselect the FLASH: Chip Select high */
	W25Qx_Disable();

	/* Wait the end of Flash writing */
	while(W25Qx_GetStatus() == W25Qx_BUSY)
	{
		/* Check for the Timeout */
    if((HAL_GetTick() - tickstart) > W25Q128FV_SECTOR_ERASE_MAX_TIME)
    {
			return W25Qx_TIMEOUT;
    }
	}
	return W25Qx_OK;
}

 /**********************************************************************************
  * 函数功能: 芯片擦除
  */
uint8_t W25Qx_Erase_Chip(void)
{
	uint8_t cmd[4];
	uint32_t tickstart = HAL_GetTick();
	cmd[0] = CHIP_ERASE_CMD;

	/* Enable write operations */
	W25Qx_WriteEnable();

	/*Select the FLASH: Chip Select low */
	W25Qx_Enable();

	/* Send the CHIP_ERASE command */
	HAL_SPI_Transmit(W25QX_SPI_Handler, cmd, 1, W25Qx_TIMEOUT_VALUE);


	/*Deselect the FLASH: Chip Select high */
	W25Qx_Disable();

	/* Wait the end of Flash writing */
	while(W25Qx_GetStatus() != W25Qx_BUSY)
	{
		/* Check for the Timeout */
    if((HAL_GetTick() - tickstart) > W25Q128FV_BULK_ERASE_MAX_TIME)
    {
			return W25Qx_TIMEOUT;
    }
	}
	return W25Qx_OK;
}


void W25QX_Test(void)
{
	uint8_t wData[0x100];   //写缓存数组
	uint8_t rData[0x100];   //读缓存数组
	uint8_t ID[4];          //设备ID缓存数组
	uint32_t i;

	printf("\r\n SPI-W25Q64 Example \r\n\r\n");

	/*-Step1- 验证设备ID  ************************************************Step1*/
	W25Qx_Init();
	W25Qx_Read_ID(ID);
    //第一位厂商ID固定0xEF,第二位设备ID根据容量不同,具体为：
     //W25Q16为0x14、32为0x15、40为0x12、64为0x16、80为0x13、128为0x17
	//if((ID[0] != 0xEF) | (ID[1] != 0x16))
	if((ID[0] != W25Q64_FLASH_ID >> 8) | (ID[1] != (W25Q64_FLASH_ID&0xFF)))
	{
		printf("something wrong in Step1 \r\n");
	}
	else
	{
		printf(" W25Qxx ID is : ");
		for(i=0;i<2;i++)
		{
			printf("0x%02X ",ID[i]);
		}
		printf("\r\n");
	}
	/*-Step2- 擦除块  ************************************************Step2*/
	if(W25Qx_Erase_Block(0) == W25Qx_OK)
		printf(" QSPI Erase Block OK!\r\n");
	else
		printf("something wrong in Step2\r\n");
	/*-Step3- 写数据  ************************************************Step3*/
	for(i =0;i<0x100;i ++)
	{
			wData[i] = i;
            rData[i] = 0;
	}

	if(W25Qx_Write(wData,0x00,0x100)== W25Qx_OK)
		printf(" QSPI Write OK!\r\n");
	else
		printf("something wrong in Step3\r\n");
    /*-Step4- 读数据  ************************************************Step4*/

	if(W25Qx_Read(rData,0x00,0x100)== W25Qx_OK)
		printf(" QSPI Read ok\r\n\r\n");
	else
		printf("something wrong in Step4\r\n");
	printf("QSPI Read Data : \r\n");
	for(i =0;i<0x100;i++)
		printf("0x%02X  ",rData[i]);
	printf("\r\n\r\n");
	/*-Step5- 数据对比  ************************************************Step5*/
	if(memcmp(wData,rData,0x100) == 0 )
		printf("W25Q64FV QuadSPI Test OK\r\n");
	else
		printf("W25Q64FV QuadSPI Test False\r\n");

}
