package com.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

public class PathFilesDemo {

	public static void main(String[] args) {
		File file = new File("E:\\Projects\\java\\jdk1.8_有道.CHM");
		Path p1 = Paths.get("E:\\\\Projects\\\\java", "jdk1.8_有道.CHM");
		System.out.println(p1);
		Path p2 = file.toPath();
		System.out.println(p2);
		Path p3 = FileSystems.getDefault().getPath("E:\\\\Projects\\\\java", "jdk1.8_有道.CHM");
		Path p4 = Paths.get("E:\\新建文本文档.txt");
		String info = "我的世界很棒";
//		try {
//			Files.write(p4, info.getBytes("utf-8"), StandardOpenOption.APPEND);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			byte[] bytes = Files.readAllBytes(p4);
			System.out.println(new String(bytes));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Files.copy(p3, Paths.get("E:\\jdk1.8_有道.CHM"),StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
