public class Text31{
	public static void main(String[] args){
		PowerA powerA = new PowerAImpl();
		PowerB powerB = new PowerBImpl();
		Adapter adapter = new Adapter(powerB);
		work(adapter);
		work(powerA);
		AnimalFunction a = new Dog();
		a.run();
	}
	
	public static void work(PowerA a){
		System.out.println("��������");
		a.insert();
		System.out.println("��������");
	} 
}

abstract class AnimalFunction implements Animal{
	public void sing(){}
	public void cry(){}
	public void run(){}
	public void swim(){}
}

interface Animal{
	public void sing();
	public void cry();
	public void run();
	public void swim();
}

class Dog extends AnimalFunction{
	public void run(){
		System.out.println("���Ƿ蹷");
	}
}

class Adapter implements PowerA{
	private PowerB powerB;
	public Adapter(PowerB powerB){
		this.powerB = powerB;
	}
	public void insert(){
		powerB.connet();
	}
}

interface PowerB{
	public void connet();
}

class PowerBImpl implements PowerB{
	public void connet(){
		System.out.println("��Դb��ʼ����");
	}
}

interface PowerA{
	public void insert();
}

class PowerAImpl implements PowerA{
	public void insert(){
		System.out.println("��Դa��ʼ����");
	}
}
