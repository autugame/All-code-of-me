#include "game.h"

static int GetCount(char mine[ROWS][COLS], int x, int y);

void InitBoard(char board[ROWS][COLS], int rows, int cols, char set)
{
    for (int i = 0; i < rows; i++)
    {
        for (int j = 0; j < cols; j++)
        {
            board[i][j] = set;
        }
    }
}

void PrintBoard(char board[ROWS][COLS], int row, int col)
{
    for (int i = 0; i <= col; i++)
    {
        printf("%d ", i);
    }
    printf("\n");
    for (int i = 1; i <= row; i++)
    {
        printf("%d ", i);
        for (int j = 1; j <= col; j++)
        {
            printf("%c ", board[i][j]);
        }
        printf("\n");
    }
}

void SetMine(char board[ROWS][COLS], int row, int col)
{
    int count = EASY_COUNT;
    while (count)
    {
        int x = rand() % row + 1;
        int y = rand() % col + 1;
        if (board[x][y] == '0')
        {
            board[x][y] = '1';
            count--;
        }
    }
}

void FindMine(char mine[ROWS][COLS], char show[ROWS][COLS], int row, int col)
{
    int x = 0;
    int y = 0;
    int win = 0;
    while (win < row * col - EASY_COUNT)
    {
        printf("Please enter the search coordinates:>>>");
        scanf("%d %d", &x, &y);
        if (x >= 1 && x <= row && y >= 1 && y <= col) 
        {
            if (mine[x][y] == '1')
            {
                printf("Game over!\n");
                PrintBoard(mine, row, col);
                break;
            }
            else
            {
                int count = GetCount(mine, x, y);
                show[x][y] = count + '0';
                PrintBoard(show, row, col);
                win++;
                break;
            }
        }
        else
        {
            printf("Re-enter\n");
        }
    }
    if (win == row * col - EASY_COUNT)
    {
        printf("Congratulation.\n");
        PrintBoard(mine, row, col);
    }
}

static int GetCount(char mine[ROWS][COLS], int x, int y)
{
    return mine[x-1][y]
         + mine[x-1][y-1]
         + mine[x][y-1]
         + mine[x+1][y-1]
         + mine[x+1][y]
         + mine[x+1][y+1]
         + mine[x][y+1]
         + mine[x-1][y+1]
         - 8 * '0';
}