public class Text27{
	public static void main(String[] args){
		BaseService user = new UserService();
		user.setISave(new FileSave());
		user.add("user");
	}
}

interface ISave{
	public void save(String data);
}

class FileSave implements ISave{
	public void save(String data){
		System.out.println("�����ݱ��浽�ļ��С�����"+data);
	}
}

class NetSave implements ISave{
	public void save(String data){
		System.out.println("�����ݱ��浽������..."+data);
	}
}

abstract class BaseService{
	private ISave iSave;
	public void setISave(ISave iSave){
		this.iSave = iSave;
	}
	public void add(String data){
		System.out.println("������ݺϷ���");
		iSave.save(data);
		System.out.println("���ݱ������");
	}
}

class UserService extends BaseService{
	
}