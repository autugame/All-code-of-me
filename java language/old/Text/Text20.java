public class Text20{
	public static void main(String[] args){
		HomeDog homeDog = new HomeDog("����");
		homeDog.eat();
		homeDog.print();
	}
}

class Dog{
	protected String name;
	protected String sex;
	public Dog(String name){
		this.name = name;
		System.out.println("���Ǹ���Ĺ��췽��");
	}
	public void eat(){
		System.out.println("�Է�");
	}
}
class HomeDog extends Dog{
	public HomeDog(String name){
		super(name);
		System.out.println("����HomeDog�Ĺ��췽��");
	}
	public void print(){
		System.out.println("����һֻ�ҹ�"+name);
	}
	public void eat(){
		super.eat();
		System.out.println("���Ǽҹ���ϲ���Թ�ͷ");
	}
}
class HuskyDog extends Dog{
	public HuskyDog(String name){
		super(name);
	}
	public void show(){
		System.out.println("���Ǹ߹�Ĺ�ʿ��");
	}
	public void eat(){
		System.out.println("���ǹ�ʿ����ϲ���Լ���");
	}
}