import java.util.Arrays;

public class Text19{
	public static void main(String[] args){
		ChickenManager cm = new ChickenManager(5);
		cm.add(new Chicken("СС",1,10));
		cm.add(new Chicken("С��",2,8));
		cm.add(new Chicken("С��",3,5));
		cm.add(new Chicken("С��",4,4));
		cm.add(new Chicken("С��",5,10));
		
		cm.add(new Chicken("С��",6,10));
		System.out.println(cm.length());
		System.out.println("----------------------------------------");
		cm.printAll();
		cm.delete(3);
		System.out.println("----------------------------------------");
		Chicken c = cm.find(6);
		c.print();
		System.out.println("----------------------------------------");
		cm.update(new Chicken("�µ�����", 1, 20));
		cm.printAll();
	}
}

//������
class ChickenManager{
	private Chicken[] cs = null;
	private int count = 0;//��¼��ǰ�����Ԫ�ظ���(�±�)
	public ChickenManager(int size){
		if(size > 0){
			cs = new Chicken[size];
		}else{
			cs = new Chicken[5];
		}
	}
	//���:ʵ�ֶ�̬����
	public void add(Chicken c){
		if(count >= cs.length){
			//�㷨1����ԭ�������һ������
			//�㷨2����ԭ�������һ��
			int newLen = cs.length*3/2+1;
			cs = Arrays.copyOf(cs, newLen);
			
		}
		cs[count++] = c;//���������Ӽ�
	}
	public int length(){
		return cs.length;
	}
	//ɾ��
	public void delete(int id){
		for(int i = 0; i < count; i++){
			if(cs[i].getId() == id){
				for(int j = i; j < count-1; j++){
					cs[j] = cs[j+1];
				}
				cs[count-1] = null;
				count--;
				break;
			}
		}
	}
	//����
	public void update(Chicken c){
		Chicken temp = find(c.getId());
		if(temp != null){
			temp.setName(c.getName());
			temp.setAge(c.getAge());
		}
	}
	//����
	public Chicken find(int id){
		for(int i = 0; i < count; i++){
			if(cs[i].getId() == id){
				return cs[i];
			}
		}
		return null;
	}
	//�������
	public void printAll(){
		for(int i = 0; i < count; i++){
			cs[i].print();
		}
	}
}

//С����
class Chicken{
	private int id;
	private String name;
	private int age;
	public Chicken(){}
	public Chicken(String name, int id, int age){
		this.name = name;
		this.id = id;
		this.age = age;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return id;
	}
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
	public void print(){
		System.out.println("id="+id+"name="+name+"age="+age);
	}
}