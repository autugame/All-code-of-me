import java.util.Scanner;
public class Text9{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		/*
		Scanner input = new Scanner(System.in);
		System.out.println("������Ҫ��ӡ��������");
		int line = input.nextInt();
		printStar(line);*/
		/*
		System.out.println("��������ݣ�");
		int year = input.nextInt();
		boolean bool = isRunNian(year);
		if(bool){
			System.out.println("����");
		}else{
			System.out.println("ƽ��");
		}
		*/
		int result = add(10, 20);
		System.out.println(result);
	}
	/*
	public static void printStar(int line){
		for (int i = 1; i <= line; i++) {//��������
			for (int k = i; k <= line; k++) {//���ƿո�ĸ���
				System.out.print(" ");
			}
			for (int j = 1; j <= 2 * i - 1; j++) {//���ƴ�ӡ��ͼ��
				System.out.print("*");
			}
			System.out.println();//���ƻ���
		}
	}*/
	public static boolean isRunNian(int year){
		if (year % 4 == 0 && year % 100 != 0 || year % 400 ==0){
			return true;
		}else{
			return false;
		}
	}
	public static int add(int a, int b){
		return a+b;
	}
	public static float add(float a, float b){
		return a+b;
	}
}