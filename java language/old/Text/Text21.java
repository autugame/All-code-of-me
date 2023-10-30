public class Text21{
	public static void main(String[] args){
		ImportCostmeticManager cm = new ImportCostmeticManager();
		cm.add(new Costmetic("���ζ�", "����", 1000));
		cm.add(new Costmetic("ʥ����", "����", 800));
		cm.add(new Costmetic("��", "����", 20));
		cm.add(new Costmetic("����ǧ��", "����", 15));
		cm.printInfo();
	}
}

//��ױƷ������
class CostmeticManager{
	protected Costmetic[] cs = new Costmetic[4];
	protected int count = 0;
	
	//��������
	public void add(Costmetic c){
		int size = cs.length;
		if(count >= size){
			int newLen = size*3/2+1;
			cs = java.util.Arrays.copyOf(cs, newLen);
		}
		cs[count++] = c;
	}
	
	//������в�Ʒ
	public void printInfo(){
		for(int i = 0; i < count; i++){
			System.out.println(cs[i].getInfo());
		}
	}
}

//�ɰ�������������
class SortCostmeticManager extends CostmeticManager{
	//����������в�Ʒ
	public void printInfo(){
		Costmetic[] temp = java.util.Arrays.copyOf(cs, count);
		int size = temp.length;
		Costmetic c = null;
		for(int i = 0; i < size-1; i++){
			for(int j = 0; j < size-i-1; j++){
				if(temp[j].getPrice() > temp[j+1].getPrice()){
					c = temp[j];
					temp[j] = temp[j+1];
					temp[j+1] = c;
				}
			}
		}
		for(Costmetic costmetic:temp){
			System.out.println(costmetic.getInfo());
		}
	}
}

class ImportCostmeticManager extends CostmeticManager{
	public void printInfo(){
		for(int i = 0; i < count; i++){
			if("����".equals(cs[i].getType())){
				System.out.println(cs[i].getInfo());
			}
		}
	}
}

//��ױƷ��
class Costmetic{
	private String name;
	private String type;
	private int price;
	
	public Costmetic(){}
	public Costmetic(String name, String type, int price){
		this.name = name;
		this.type = type;
		this.price = price;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return type;
	}
	public void setPrice(int price){
		this.price = price;
	}
	public int getPrice(){
		return price;
	}
	public String getInfo(){
		return "name="+name+"\ttype="+type+"\tprice="+price;
	}
}