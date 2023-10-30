package com.autuo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ObjectStreamDemo {
	
	public static void main(String[] args) {
		WriteObjects();
		readObjects();
	}
	
	private static void WriteObjects() {
		Dog dog = new Dog("wangwang", 2, "母");
		Dog dog2 = new Dog("哈士奇", 3, "公");
		Dog[] dogs = { dog, dog2};
		File file = new File("D:/Dog.obg");
		try {
			OutputStream out = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(out);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(dogs);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void readObjects() {
		File file = new File("D:/Dog.obg");
		try {
			InputStream in = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(in);
			ObjectInputStream ois = new ObjectInputStream(bis);
			Dog[] dog = (Dog[])ois.readObject();
			ois.close();
			for (Dog d : dog) {
				System.out.println(d.toString());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
