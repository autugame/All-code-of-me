public class Text26{
	public static void main(String[] args){
		UserManager um = new UserManager();
		um.action("test", "add");
	}
}

abstract class BaseManager{
	
	public void action(String name,String method){
		if("admin".equals(name)){
			execute(method);
		}else{
			System.out.println("��û�в���Ȩ��,����ϵ����Ա!!");
		}
	}
	
	public abstract void execute(String method);
}

class UserManager extends BaseManager{
	public void execute(String method){
		//�û��Ƿ��¼
		if("add".equals(method)){
			System.out.println("ִ������Ӳ���");
		}else if("del".equals(method)){
			System.out.println("ִ����ɾ������");
		}
	}
}

class Class{
	
}