import java.util.Scanner;
public class Text7{
	public static void main(String[] args){
		System.out.println("������ɼ�");
		Scanner input = new Scanner(System.in);
		int score = input.nextInt();
		String a = score >= 60 ? "����" : "������";
		System.out.println(a);
	}
}