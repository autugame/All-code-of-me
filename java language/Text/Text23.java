public class Text23{
	public static void main(String[] args){
		Man man = new Man();
		man.move();
		man.eat();
		
		Women women = new Women();
		women.move();
		women.eat();
	}
}

abstract class Animal{
	public abstract void move();//������
}

abstract class Person extends Animal{
	public abstract void eat();
}

class Man extends Person{
	public void move(){
		System.out.println("���������Ұ��ܲ�");
	}
	public void eat(){
		System.out.println("���������Ұ�����");
	}
}

class Women extends Person{
	public void move(){
		System.out.println("���������Ұ����");
	}
	public void eat(){
		System.out.println("���������Ұ���ˮ��");
	}
}