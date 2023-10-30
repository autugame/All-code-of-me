package com.autuo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class CharStringDemo {
	
	public static void main(String[] args) {
		out(new File("D:/新建 文本文档.txt"));
		in(new File("D:/新建 文本文档.txt"));
	}
	
	private static void out(File f) {
		try {
			Writer out = new FileWriter(f, true);
			out.write("小河流水哗啦啦，村姑晚上到我家");
			System.out.println("文件写入完毕");
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void in(File f) {
		try {
			Reader in = new FileReader(f);
			char[] cs = new char[5];
			int len = -1;
			StringBuilder buf = new StringBuilder();
			while ((len = in.read(cs)) != -1) {
				buf.append(new String(cs, 0, len));
			}
			System.out.println(buf);
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
