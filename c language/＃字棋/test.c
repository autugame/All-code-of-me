#include "time.h"

#include "game.c"

void Menu();

void Game();

int main()
{
    srand((unsigned int) time(NULL));

    int input = 0;
    do
    {
        Menu();

        printf("Please select:>>>");
        scanf("%d", &input);

        switch (input)
        {
            case 1:
                Game();
                break;
            case 0:
                printf("Exit\n");
                break;
            default:
                printf("Wrong selection, reselect!");
                break;
        }
    } while (input);
    
    return 0;
}

void Menu()
{
    printf("***************************************\n");
    printf("************** 1.Play *****************\n");
    printf("************** 0.Exit *****************\n");
    printf("***************************************\n");
}

void Game()
{
    char board[ROW][COL];
    //Initialization.
    InitBoard(board, ROW, COL);
    //Print board.
    PrintBoard(board, ROW, COL);
    char ret = 0;
    while (1)
    {
        //Player turn.
        PlayerMove(board, ROW, COL);
        PrintBoard(board, ROW, COL);
        ret = IsWin(board, ROW, COL);
        if (ret != 'C')
            break;
        //Computer turn.
        ComputerMove(board, ROW, COL);
        PrintBoard(board, ROW, COL);
        ret = IsWin(board, ROW, COL);
        if (ret != 'C')
            break;
    }
    switch (ret)
    {
        case '*':
            printf("Player wins.\n");
            break;
        case '#':
            printf("PC wins.\n");
            break;
        default:
            printf("A tie.\n");
    }
}
