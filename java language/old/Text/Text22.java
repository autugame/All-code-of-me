public class Text22{
	public static void main(String[] args){
		System.out.println(Constant.PERSON_NUM);
		Constant Cs = new Constant();
		Cs.setLength(10);
	}
}

class Constant{
	public static final int PERSON_NUM = 10;//����
	public void setLength(final int size){
		size++;
		
	}
}

final class FinalClass{
	public final int DAY_NUMBER = 20;//��������
	public final void print(){
		System.out.println("����finla����");
	}
}