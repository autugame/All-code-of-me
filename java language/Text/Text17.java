public class Text17{
	public static void main(String[] args){
		Role beibei = new Role("����");
		Role yunchang = new Role("����");
		Role feifei = new Role("�ŷ�");
		Role.country = "κ";
		System.out.println(beibei.getInfo());
		System.out.println(yunchang.getInfo());
		System.out.println(feifei.getInfo());
	}
}

class Role{
	private String name;
	static String country = "���";//��̬����ȫ�ֱ���
	public Role(){};
	public Role(String name/*, String country*/){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	/*
	public String getCountry(){
		return country;
	}*/
	public static void setCountry(String country){
		Role.country = country;
	}
	public String getInfo(){
		return "����"+name+"����"+country;
	}
}