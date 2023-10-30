public class Text24{
	public static void main(String[] args){
		Girl girl = new Girl("С��");
		girl.eat();
		girl.sleep();
		girl.run();
		girl.print();
		System.out.println(girl.name);
	}
}

interface IEat{
	public abstract void eat();//�ӿ���ֻ�ܶ�����󷽷�
	void sleep();//�ӿ��ж���ķ���û���������η�
	public static final int NUM = 10;
	int NUMBER = 10;
	public default void print(){//�����Կ��Ա�����ʵ����̳�
		System.out.println("eat");
	}
}

interface IRun{
	void run();
}

interface ISleep extends IEat,IRun{
	void sleep();
}

class Girl implements ISleep,IEat{
	public String name;
	public Girl(){}
	public Girl(String name){
		this.name = name;
	}
	public void sleep(){
		System.out.println("�Ұ�˯��");
	}
	public void eat(){
		System.out.println("����"+name+"�Ұ������");
	}
	public void run(){
		System.out.println("�������");
	}
}
