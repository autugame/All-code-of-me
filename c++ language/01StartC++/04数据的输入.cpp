#include <iostream>
#include <string>
#include <ctime>
using namespace std;

int main4()
{
	int a = 10;
	cout << "�������������" << a << endl;
	cin >> a;
	cout << a << endl;
	string str = "���";
	cin >> str;
	cout << str << endl;
	int a1 = 3;
	int a2 = 3;
	cout << a1 % a2;
	system("pause");
	return 0;
}

int main()
{
	/*int a = 10;
	if (a != 10)
	{
		cout << "��ȴ" << endl;
	}
	else
	{
		cout << "ɵ��" << endl;
	}*/

	/*int num = 0;
	while (num < 10)
		{
		cout << num << endl;
	num++;
	}*/

	//���������
	/*srand((unsigned int)time(NULL));
	int num = rand() % 100 + 1;
	int val = 0;
	int cut = 0;
	cout << "\t������\n\a��ֻ��8�λ���" <<  endl;
	while (true)
	{
		if (cut >= 8)
		{
			break;
		}
		cin >> val;
		if (num == val)
		{
			cout << "��ϲ��¶���\a" << endl;
			break;
		}
		else
		{
			if (val > num)
			{
				cout << "�´���" << endl;
			}
			else
			{
				cout << "��С��" << endl;
			}
		}
		cut++;
	}*/
	/*int num = 100;
	int a = 0;
	int b = 0;
	int c = 0;
	do
	{
		a = 0;
		b = 0;
		c = 0;
		
		a = num % 10;
		b = num / 10 % 10;
		c = num / 100;

		if (a * a * a + b * b * b + c * c * c == num)
		{
			cout << num << endl;
		}
		num++;
	} while (num < 1000);*/
	//int num = 0;

	for (int i = 0; i <= 100; i++)
	{
		if (i % 7 == 0 || i % 10 == 7 || i / 10 == 7)
		{
			cout << "������" << endl;
		}
		else
		{
			cout << i << endl;
		}
	}

	system("pause");
	return 0;
}