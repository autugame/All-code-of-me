//import java.lang.ClassCastException;
public class Text25{
	public static void main(String[] args){
		//�ø��������ָ����������ô�����ͱ�ʾС������
		Chicken hc = new HomeChicken("С��");
		//hc.eat();
		
		Chicken yc = new YeChicken("�󼦼�");
		Chicken jjc = new JianJiaoChicken("�⽷��");
		//yc.eat();
		//hc = yc;
		//hc.eat();
		eat(hc);
		eat(yc);
		eat(jjc);
	}
	public static void eat(Chicken c){
		System.out.println("���Է�");
		c.eat();
		if(c instanceof JianJiaoChicken){
		JianJiaoChicken jjc = (JianJiaoChicken)c;
		jjc.song();
		}
	}
}

abstract class Chicken{
	private String name;
	public Chicken(){}
	public Chicken(String name){
		this.name = name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public abstract void eat();
}

class HomeChicken extends Chicken{
	public HomeChicken(String name){
		super(name);
	}
	public void eat(){
		System.out.println(this.getName()+"�Ұ�����");
	}
}

class YeChicken extends Chicken{
	public YeChicken(String name){
		super(name);
	}
	public void eat(){
		System.out.println(this.getName()+"�Ұ��Գ���");
	}
}

class JianJiaoChicken extends Chicken{
	public JianJiaoChicken(String name){
		super(name);
	}
	public void eat(){
		System.out.println(this.getName()+"�Ұ����Զ���");
	}
	public void song(){
		System.out.println("����ֻ��һֻ��");
	}
}