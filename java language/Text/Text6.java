import java.util.Scanner;
public class Text6{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("����������");
		int day = input.nextInt();
		System.out.println("һ����������" + (day / 7));
		System.out.println("ʣ���������" + (day % 7));
	}	
}