#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main()
{
	srand(time(0));
	int number = rand() % 100 + 1;
	int count = 0;
	int a = 0;
	printf("������˿����°�\n");
	do {
		printf("�²����һ��100����\n");
		scanf_s("%d", &a);
		count++;
		if (a > number) {
			printf("�´���\n");
		}
		else if (a < number) {
			printf("��С��\n");
		}
	} while (a != number);
	printf("�����%d��\n", count);
	return 0;
}