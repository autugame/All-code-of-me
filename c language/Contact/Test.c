#include "Contact.h"

void menu();

enum Option
{
    EXIT,
    ADD,
    DEL,
    SEARCH,
    MODIFY,
    SORT,
    PRINT
};

int main()
{
    int input = 0;
    //init
    Contact con;
    InitContact(&con);
    do
    {
        menu();
        printf("Please choose:>>>");
        scanf("%d", &input);
        Contact con;
        switch (input)
        {
            case ADD:
                AddContact(&con);
                break;
            case DEL:
                DelContact(&con);
                break;
            case SEARCH:
                SearchContact(&con);
                break;
            case MODIFY:
                ModifyContact(&con);
                break;
            case SORT:
                break;
            case PRINT:
                PrintContact(&con);
                break;
            case EXIT:
                printf("Exited!");
                break;
            default:
                printf("Error!");
                break;
        }
    } while (input);
    
    return 0;
}

void menu()
{
    printf("*****************************************\n");
    printf("**********     1.Add 2.Del     **********\n");
    printf("**********  3.Search 4.Modify  **********\n");
    printf("**********    5.Sort 6.Print   **********\n");
    printf("**********    0.Exit           **********\n");
    printf("*****************************************\n");
}