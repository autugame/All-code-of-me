#include <stdio.h>

#define NAME_MAX 20
#define SEX_MAX 10
#define TELE_MAX 10
#define ADDR_MAX 10
#define MAX 1000

typedef struct PeoInfo
{
    char name[NAME_MAX];
    char sex[SEX_MAX];
    int age;
    char tele[TELE_MAX];
    char addr[ADDR_MAX];
} PeoInfo;

typedef struct Contact
{
    PeoInfo data[MAX];
    int sz;
} Contact;

void InitContact(Contact* pc);
void AddContact(Contact* pc);
void PrintContact(const Contact* pc);
void DelContact(Contact* pc);
void SearchContact(Contact* pc);
void ModifyContact(Contact* pc);