#include <string.h>

#include "Contact.h"

static int FindByName(Contact* pc, char name[]);

void InitContact(Contact* pc)
{
    pc->sz = 0;
    memset(pc->data, 0, sizeof(pc->data));
}

void AddContact(Contact* pc)
{
    if (pc->sz == MAX)
    {
        printf("Contacts Full!\n");
        return;
    }
    //Add one
    printf("Please enter your name:>>>");
    scanf("%s", pc->data[pc->sz].name);
    printf("Please enter your age:>>>");
    scanf("%d", &pc->data[pc->sz].age);
    printf("Please enter your gender:>>>");
    scanf("%s", pc->data[pc->sz].sex);
    printf("Please enter your phone number:>>>");
    scanf("%s", pc->data[pc->sz].tele);
    printf("Please enter your address:>>>");
    scanf("%s", pc->data[pc->sz].addr);

    pc->sz++;
    printf("Successfully added");
}

void PrintContact(const Contact* pc)
{
    printf("%-20s\t %-5s\t %-5s\t %-12s\t %-20s\n", "Name", "Age", "Sex", "Tele", "Addr");
    for (int i = 0; i < pc->sz; i++)
    {
        printf("%-20s\t %-5d\t %-5s\t %-12s\t %-20s\n", pc->data[i].name, pc->data[i].age, pc->data[i].sex, pc->data[i].tele, pc->data[i].addr);
    }
}

void DelContact(Contact* pc)
{
    char name[NAME_MAX] = {0};
    if (pc->sz == 0)
    {
        printf("No need to delete!");
        return;
    }
    printf("Please enter the name of the one to be deleted:>>>");
    scanf("%s", name);
    //Find the one you want to delete.
    int pos = FindByName(pc, name);
    if (pos == -1)
    {
        printf("Does not exist!\n");
        return;
    }
    for (int i = pos; i < pc->sz-1; i++)
    {
        pc->data[i] = pc->data[i+1];
    }
    pc->sz--;
    printf("Successfully deleted!\n");
}

static int FindByName(Contact* pc, char name[])
{
    for (int i = 0; i < pc->sz; i++)
    {
        if (strcmp(pc->data[i].name, name) == 0)
        {
            return i;
        }
    }
    return -1;
}

void SearchContact(Contact* pc)
{
    char name[NAME_MAX] = {0};
    printf("Please enter the name you want to search for:>>>");
    scanf("%s", name);
    int pos = FindByName(pc, name);
    if (pos == -1)
    {
        printf("Does not exist!\n");
        return;
    }
    printf("%-20s\t %-5d\t %-5s\t %-12s\t %-20s\n", pc->data[pos].name, pc->data[pos].age, pc->data[pos].sex, pc->data[pos].tele, pc->data[pos].addr);
}

void ModifyContact(Contact* pc)
{
    char name[NAME_MAX] = {0};
    printf("Please enter the name you want to modify:>>>");
    scanf("%s", name);
    int pos = FindByName(pc, name);
    if (pos == -1)
    {
        printf("Does not exist!\n");
        return;
    }
    printf("Please enter your name:>>>");
    scanf("%s", pc->data[pos].name);
    printf("Please enter your age:>>>");
    scanf("%d", &pc->data[pos].age);
    printf("Please enter your gender:>>>");
    scanf("%s", pc->data[pos].sex);
    printf("Please enter your phone number:>>>");
    scanf("%s", pc->data[pos].tele);
    printf("Please enter your address:>>>");
    scanf("%s", pc->data[pos].addr);
    printf("Successfully modified!");
}