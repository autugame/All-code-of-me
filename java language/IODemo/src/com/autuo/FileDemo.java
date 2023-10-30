package com.autuo;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

public class FileDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f1 = new File("D:" + File.separator + "Projects" + File.separator + "Text" + File.separator + "autuo.txt");
		if (!f1.exists()) {
			try {
				f1.createNewFile();
				System.out.println("文件创建成功");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("文件已存在");
		}
		//System.out.println(f1.isFile());
		File f2 = new File("D:\\Projects\\Text\\my");
		//System.out.println(f2.delete());
		String[] names = f2.list();
		for (String s : names) {
			System.out.println(s);
		}
		File[] fs = f2.listFiles();
		for (File f : fs) {
			System.out.println(f);
			System.out.println("length=" + f.length());
			System.out.println("name=" + f.getName());
			System.out.println("相对路径=" + f.getPath());
			System.out.println("绝对路径=" + f.getAbsolutePath());
			System.out.println("是否是隐藏文件=" + f.isHidden());
			System.out.println("是否可读=" + f.canRead());
			Date date = new Date(f.lastModified());
			DateFormat df = new SimpleDateFormat("HH:mm:ss");
			System.out.println("最后修改时间=" + df.format(date));
			System.out.println("------------------------------");
		}
		File f3 = new File("text.txt");
		System.out.println(f3.getPath());
		System.out.println(f3.getAbsolutePath());
		File f4 = new File("D:\\Projects\\Text\\mm");
		System.out.println(f4.mkdirs());
		//f4.renameTo(new File("D:\\Projects\\Text\\mmdo"));
		f4.renameTo(new File("D:\\Projects\\mmdo"));
		f4.mkdirs();
		

		File[] files = f4.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				pathname.getName().endsWith(".txt");
				return false;
			}
			
		});
		//File f5 = new File("D:\\Projects\\Text\\my");
		//File[] files2 = f5.listFiles((pathname) -> pathname.getName().endsWith(".txt"));
		System.out.println("--------");
		//System.out.println(Arrays.toString(files));
		for (File f : files) {
			System.out.println(f.getName());
		}
	}

}
