import java.util.Scanner;//���ܼ�������Ĺ�����


public class Text2{
	public static void main(String[] args){
		System.out.println("ת���ַ�����");
		System.out.print("�������һ���س���\n\r");
		int x = 1;
		int y = 2;
		y = x + y - (y++);
		System.out.println(y);
		System.out.println(1 % 2);
		System.out.println(1 / 2);
		short s = 10;
		//s = (short)(s + 1);
		//s += 1;
		System.out.println(s);
		//����ֵΪboolean����
		System.out.println(x == y);
		//����һ����������Ĺ���
		Scanner input = new Scanner(System.in);
		System.out.println("������һ������");
		int x1 = input.nextInt();//�Ӽ��̽���һ������
		System.out.println(x1);
		System.out.println("������һ���ַ���");
		String s1 = input.next();
		System.out.println(s1);
	}
}