import java.util.Scanner;
public class Text8{
	public static void main(String[] args){
		/*
		System.out.println("������һ����ĸ");
		Scanner input = new Scanner(System.in);
		int c = input.nextInt();
		if (c >= 65 && c <= 90){
			System.out.println("��д" + (char)c);
		}
		else{
			System.out.println("Сд" + (char)c);
		}
		*/
		//System.out.println("���������");
		Scanner input = new Scanner(System.in);
		/*
		int year = input.nextInt();
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0){
			System.out.println("������");
		}else{
			System.out.println("ƽ��");
		}*/
		/*System.out.println("����һ���ɼ�");
		int score = input.nextInt();
		if(score >= 90){
			System.out.println("A");
		}else if (score >= 80){
			System.out.println("B");
		}else if (score >= 70){
			System.out.println("C");
		}else if (score >= 60){
			System.out.println("D");
		}else{
			System.out.println("E");
		}*/
		/*System.out.println("�������һ����");
		int num1 = input.nextInt();
		System.out.println("������ڶ�����");
		int num2 = input.nextInt();
		if(num1 > num2){
			System.out.println("��һ�������ڵڶ�����");
		}else if(num1 < num2){
			System.out.println("��һ����С�ڵڶ�����");
		}else if(num1 == num2){
			System.out.println("��һ�������ڵڶ�����");
		}
		System.out.println("��������������(1.��, 2.��)");
		int weather = input.nextInt();
		if(weather == 1){
			System.out.println("����Ϸ");
			int flag = input.nextInt();
			if(flag == 1){
				System.out.println("���");
			}else{
				System.out.println("��Ů����");
			}
		}else{
			System.out.println("˯��");
			int flag = input.nextInt();
			if(flag == 1){
				System.out.println("����Ӱ");
			}else{
				System.out.println("������");
			}
		}*/
		/*
		System.out.println("���������ڼ�");
		int day = input.nextInt();
		switch(day){
			case 1:
				System.out.println("����һ");
				System.out.println("1.������2.����");
				int method = input.nextInt();
				if(method == 1){
					System.out.println("����");
				}else{
					System.out.println("����");
				}
				break;
			case 2:
				System.out.println("����һ");
				break;
			case 3:
				System.out.println("����һ");
				break;
			case 4:
				System.out.println("����һ");
				break;
			case 5:
				System.out.println("����һ");
				break;
			case 6:
				System.out.println("����һ");
				break;
			case 7:
				System.out.println("����һ");
				break;
			default:
				System.out.println("������Ĳ������ֻ����Ǵ�д����������������");
				break;*/
		
		//}
		/*
		System.out.println("�������·�");
		int month = input.nextInt();
		switch(month){
			case 1:
				System.out.println("31");
				break;
			case 2:
				System.out.println("28");
				break;
			case 3:
				System.out.println("31");
				break;
			case 4:
				System.out.println("30");
				break;
			case 5:
				System.out.println("31");
				break;
			case 6:
				System.out.println("30");
				break;
			case 7:
				System.out.println("31");
				break;
			case 8:
				System.out.println("31");
				break;
			case 9:
				System.out.println("30");
				break;
			case 10:
				System.out.println("31");
				break;
			case 11:
				System.out.println("30");
				break;
			case 12:
				System.out.println("31");
				break;
			default:
			System.out.println("�Ҳ�������˵����ʲô��˼");
			break;
		}*/
		/*
		System.out.println("�������һ����");
		int num1 = input.nextInt();
		System.out.println("������ڶ�����");
		int num2 = input.nextInt();
		System.out.println("�����������");
		String op = input.next();
		switch(op){
			case "��":
				System.out.println(num1 + num2);
				break;
			case "��":
				System.out.println(num1 - num2);
				break;
			case "��":
				System.out.println(num1 * num2);
				break;
			case "��":
					System.out.println(num1 / num2);
				break;
			default:
				System.out.println("������Ķ����Ҳ���ʶ!!!");
				break;
		}*/
		/*
		int num = 0;
		int i = 1;
		while(i <= 100){
			num += i;
			i++;
		}
		System.out.println(num);
		*/
		/*
		int i = 1;
		int num = 1;
		while(i <= 10){
			num *= i;
			i++;
		}
		System.out.println(num);
		*/
		/*
		int i = 1;
		while(i < 100){
			if(i % 2 == 0){
				System.out.println("ż��:" + i);
			}else{
				System.out.println("����:" + i);
			}
			if(i % 3 == 0){
				System.out.println("3�ı���:" + i);
			}
			i++;
		}*/
		/*
		int i = 10;
		do{
			//ѭ����
			System.out.println(i);
			i--;
		}while(i >= 0);*/
		/*
		int num = 0;
		int i = 1;
		do{
			int j = 1;
			int jc = 1;
			while(j <= i){
				jc *= j;
				j++;
			}
			num += jc;
			i++;
		}while(i <= 10);
		System.out.println(num);
		*/
		/*
		for(int i = 0; i < 10; i++){
			if(i == 5){
				continue;
			}
			System.out.println(i);
		}*/
		/*
		for(int i = 0; i < 200; i++){
			if(i % 7 ==0 && i % 4 != 0){
				System.out.println(i);
			}
		}*/
		/*
		int n1 = 1;
		int n2 = 1;
		int next = 0;
		System.out.print(n1 + " " + n2 + " ");
		for(int i = 0; i < 8; i++){
			next = n1 + n2;
			System.out.print(next + " ");
			n1 = n2;
			n2 = next;
		}*/
		/*
		for(int i = 1; i <= 5; i++){
			for(int j = 1; j <= i; j++){
				System.out.print("*");
			}
			System.out.println();
		}
		for(int i = 1; i <= 9; i++){
			for(int j = 1; j <= i; j++){
				System.out.print(j + "*" + i + "=" + i * j + "\t");
			}
			System.out.println();
		}
		for(int i = 1; i <= 5; i++){
			for(int j = 5; j > 0; j--){
				if(j <= i){
					System.out.print("*");
				}else{
					System.out.print (" ");
				}
			}
			System.out.println();
		}
		for (int i = 1; i <= 5; i++) {//��������
			for (int k = i; k <= 5; k++) {//���ƿո�ĸ���
				System.out.print(" ");
			}
			for (int j = 1; j <= 2 * i - 1; j++) {//���ƴ�ӡ��ͼ��
				System.out.print("*");
			}
			System.out.println();//���ƻ���
		}*/
	}
}