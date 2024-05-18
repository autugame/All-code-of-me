#include "game.h"

int IsFull(char board[ROW][COL], int row, int col);

void InitBoard(char board[ROW][COL], int row, int col)
{
    for (int i = 0; i < row; i++)
    {
        for (int j = 0; j < col; j++)
        {
            board[i][j] = ' ';
        }
    }
}

void PrintBoard(char board[ROW][COL], int row , int col)
{
    for (int i = 0; i < row; i++)
    {
        for (int j = 0; j < col; j++)
        {
            printf(" %c ", board[i][j]);
            if (j < col - 1)
                printf("|");
        }
        printf("\n");
        if (i < row - 1)
        {
            for (int j = 0; j < col; j++)
            {
                printf("---");
                if (j < col - 1)
                    printf("|");
            }
            printf("\n");
        }
    }
}

void PlayerMove(char board[ROW][COL], int row, int col)
{
    int x = 0;
    int y = 0;
    printf("Player turn.\n");
    while (1)
    {
            printf("Please enter coordinates:>>>");
            scanf("%d %d", &x, &y);
        if (x >= 1 && x <= row && y >= 1 && y <= col)
        {
            if (board[x - 1][y - 1] == ' ')
            {
                board[x - 1][y - 1] = '*';
                break;
            }
        }
        else
        {
            printf("Re-enter!\n");
        }
    }
    
}

void ComputerMove(char board[ROW][COL], int row, int col)
{
    printf("Computer turn.\n");
    
    while (1)
    {
        int x = rand() % row;
        int y = rand() % col;

        if (board[x][y] == ' ')
        {
            board[x][y] = '#';
            break;
        }
    }
}

char IsWin(char board[ROW][COL], int row, int col)
{
    for (int i = 0; i < row; i++)
    {
        if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ')
        {
            return board[i][0];
        }
    }
    for (int i = 0; i < col; i++)
    {
        if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[i][0] != ' ')
        {
            return board[0][i];
        }
    }
    if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ')
    {
        return board[0][0];
    }
    if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ')
    {
        return board[0][2];
    }
    if (IsFull(board, row, col))
        return 'Q';
    return 'C';
}

int IsFull(char board[ROW][COL], int row, int col)
{
    for (int i = 0; i < row; i++)
    {
        for (int j = 0; j < col; j++)
        {
            if (board[i][j] == ' ')
                return 0;
        }
    }
    return 1;
}