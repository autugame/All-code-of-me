public class Text15{
	public static void main(String[] args){
		
		Horse h = null;
		h = new Horse();
		h.name = "������";
		h.age = 350;
		h.run();
		/*
		h.run();
		h.eat();
		name("����");
		new Horse().eat();//��������ֻ����һ��
		h = null;//�ͷŶ���
		Horse h1 = null;
		Horse h2 = null;
		h1 = new Horse();
		//h2 = new Horse();
		h1.name = "С��";
		h1.age = 4;
		//h2.name = "С��";
		//h2.age = 5;
		h2 = h1;
		*/
		Person p1 = new Person("С��", 2);
		p1.setName("С��");
		p1.setAge(18);
	}
	public static void name(String name){
		System.out.println("����"+name);
	}
}
class Person{
	public Person(){
		System.out.println("�װ��Ĺ������������Ǻ�");
	}
	public Person(String name){
		this.name = name;
	}
	public Person(String name, int age){
		this();//�����������췽��ʱ���������ڵ�һ������
		this.name = name;
		this.age = age;
	}
	private String name;
	private int age;
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setAge(int age){
		this.age = age;
	}
	public int getAge(){
		return age;
	}
}

class Horse{
	String name;
	int age;
	
	public void run(){
		System.out.println("����"+name+"��"+age+"������ǧ��");
		this.eat();
	}
	public void eat(){
		System.out.println("�ҳ��ɲݶ��ҳԵĶ�");
	}
}