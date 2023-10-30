import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class Unionlotto{
	public static void main(String[] args){
		//������صı���
		int[] userRedBall = new int[6];//�û�ѡ��ĺ������
		int[] sysRedBall = new int[6];//ϵͳ���ɵĺ������
		int userBlueBall = 0;//�û�ѡ�������
		int sysBlueBall = 0;//ϵͳ���ɵ�����
		int redCount = 0;//��¼�û�ѡ����ȷ�ĺ�����
		int blueCount = 0;//��¼�û�ѡ����ȷ��������
		int[] redBall = new int[33];//���ڴ洢1-33�ĺ������
		//��Ҫ�������6����1-33֮�䲻�ظ�����(�㷨)
		for(int i = 0; i < redBall.length; i++){
			redBall[i] = i+1;
		}
		
		//��Ϸ��ʼϵͳ��ʾ
		System.out.println("˫ɫ����Ϸ��ʼ,good luck!!");
		System.out.println("��������Ҫ��ѡ����ѡ��1����ѡ��2����ѡ");
		Scanner input = new Scanner(System.in);
		Random r = new Random();
		boolean flag = true;
		while(flag){
			int isAuto = input.nextInt();
			switch(isAuto){
				case 1:
				//��ѡ
					computerSelection(redBall, userRedBall);//����
					userBlueBall = r.nextInt(16)+1;
					flag = false;
					break;
				case 2:
				//��ѡ
					System.out.println("��ѡ��6���������(1-33)");
					for(int i = 0; i < userRedBall.length; i++){
						userRedBall[i] = input.nextInt();
					}
					System.out.println("��ѡ��1-16֮����������");
					userBlueBall = input.nextInt();
					flag = false;
					break;
				default:
					System.out.println("��������Ҫ��ѡ����ѡ��1����ѡ��2����ѡ");
					break;
			}
		}
		//ϵͳ������ɺ���
		//����
		computerSelection(redBall, sysRedBall);
		sysBlueBall = r.nextInt(16)+1;
		//ͳ�ƽ��
		//ͳ�ƺ���
		for(int i = 0; i < userRedBall.length; i++){
			for(int j = 0; j < sysRedBall.length-redCount; j++){
				if(userRedBall[i] == sysRedBall[j]){
					int temp = sysRedBall[j];
					sysRedBall[j] = sysRedBall[sysRedBall.length-1-redCount];
					sysRedBall[sysRedBall.length-1-redCount] = temp;
					redCount++;
					break;
				}
			}
		}
		//ͳ������
		if(userBlueBall == sysBlueBall){
			blueCount = 1;
		}
		//��֤�Ƿ��н�
		if(blueCount == 0 && redCount <= 3){
			System.out.println("���ź������󽱴�ʧ��Ե");
		}else if(blueCount == 1 && redCount < 3){
			System.out.println("��ϲ�㣡�������Ƚ�5Ԫ");
		}else if((blueCount == 1 && redCount == 3) || (blueCount == 0 && redCount == 4)){
			System.out.println("��ϲ�㣡������Ƚ�10Ԫ");
		}else if((blueCount == 1 && redCount == 4) || (blueCount == 0 && redCount == 5)){
			System.out.println("��ϲ�㣡�����ĵȽ�200Ԫ");
		}else if((blueCount == 1 && redCount == 5)){
			System.out.println("��ϲ�㣡�������Ƚ�1000Ԫ");
		}else if((blueCount == 0 && redCount == 6)){
			System.out.println("��ϲ�㣡�������Ƚ�1500000Ԫ");
		}else if((blueCount == 1 && redCount == 6)){
			System.out.println("��ϲ�㣡�������Ƚ�5000000Ԫ");
		}else{
			System.out.println("ϵͳ�����н���Ч!!!");
		}
		//����ϵͳ����
		System.out.println("�����н��������");
		sort(sysRedBall);
		System.out.println(Arrays.toString(sysRedBall));
		System.out.println("�����н��������" + sysBlueBall);
		
		//�����û�����
		System.out.println("������ѡ��ĺ������");
		sort(userRedBall);
		System.out.println(Arrays.toString(userRedBall));
		System.out.println("������ѡ����������" + userBlueBall);
	}
	public static void sort(int[] ball){
		for(int i = 0; i < ball.length-1; i++){
			for(int j = 0; j < ball.length-1-i; j++){
				if(ball[j] > ball[j+1]){
					ball[j] = ball[j]+ball[j+1];
					ball[j+1] = ball[j] - ball[j+1];
					ball[j] = ball[j] - ball[j+1];
				}	
			}
		}
	}
	//������ָ�������У�������ɶ�����ظ�����
	public static void computerSelection(int[] redBall, int[] userRedBall){
		Random r = new Random();
		int index = -1;
		for(int i = 0; i < userRedBall.length; i++){
			index = r.nextInt(redBall.length-i);
			userRedBall[i] = redBall[index];
			
			int temp = redBall[index];
			redBall[index] = redBall[redBall.length-1-i];
			redBall[redBall.length-1-i] = temp;
		}
	}
}