#include <stdio.h>
#include <stdlib.h>

//Symbol definition.
#define ROW 3
#define COL 3

//Function declaration.
//Initialization board.
void InitBoard(char board[ROW][COL], int row, int col);
//Print board.
void PrintBoard(char board[ROW][COL], int row, int col);
//Player turn.
void PlayerMove(char board[ROW][COL], int row, int col);
//Computer turn.
void ComputerMove(char board[ROW][COL], int row, int col);
//Determine if the game is won or lost.
char IsWin(char board[ROW][COL], int row, int col);