import java.util.Scanner;
public class Text4{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("������Բ����ĸ�:");
		int h = input.nextInt();
		System.out.println("������Բ����ĵ���뾶:");
		int r = input.nextInt();
		double result = 3.14 * r * r * h;
		System.out.println(result);
	}
}