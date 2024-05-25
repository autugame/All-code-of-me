#include "game.h"

void menu();
void game();

int main()
{
    int input = 0;
    srand((unsigned int) time(NULL));
    do
    {
        menu();
        printf("Please enter:>>>");
        scanf("%d", &input);
        switch (input)
        {
            case 1:
                game();
                break;
            case 0:
                printf("exit");
                break;
            default:
                printf("A unknow number.\n");
                break;
        }
    } while (input);

    return 0;
}

void menu()
{
    printf("***********************************\n");
    printf("************** 1.Play *************\n");
    printf("************** 0.Exit *************\n");
    printf("***********************************\n");
}

void game()
{
    char mine[ROWS][COLS] = {0};
    char show[ROWS][COLS] = {0};
    //Initialization.
    InitBoard(mine, ROWS, COLS, '0');
    InitBoard(show, ROWS, COLS, '*');
    //Print board.
    //PrintBoard(mine, ROW, COL);
    PrintBoard(show, ROW, COL);

    SetMine(mine, ROW, COL);
    //PrintBoard(mine, ROW, COL);

    FindMine(mine, show, ROW, COL);
}