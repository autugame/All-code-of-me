import java.util.Scanner;
import java.util.Random;
public class Text11{
	public static void main(String[] args){
		int[] nums = new int[5];
		int len = nums.length;
		Random r = new Random();
		for(int i = 0; i < len; i++){
			nums[i] = r.nextInt(60);
		}
		Scanner input = new Scanner(System.in);
		System.out.println("��������Ҫ�µ���60����");
		int userNum = input.nextInt();
		boolean flag = false;
		for(int x:nums){
			if(userNum == x){
				flag = true;
				break;
			}
		}
		if(flag){
			System.out.println("��ϲ��¶���");
		}else{
			System.out.println("û�¶�");
		}
	}
}