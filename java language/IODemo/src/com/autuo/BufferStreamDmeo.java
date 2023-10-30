package com.autuo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

@SuppressWarnings("unused")
public class BufferStreamDmeo {
	private static void byteWreter(String f) {
		File file = new File(f);
		try {
			OutputStream out = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(out);
			String info = "小河挺尴尬";
			bos.write(info.getBytes());
			bos.close();
			System.out.println("写入成功");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void byteReader(String f) {
		File file = new File(f);
		try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
			byte[] bytes = new byte[1024];
			int len = -1;
			StringBuilder buf = new StringBuilder();
			while ((len = bis.read(bytes)) != -1) {
				buf.append(new String(bytes, 0, len));
			}
			bis.close();
			System.out.println(buf);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void charReader(String f) {
		try {
			Reader reader = new FileReader(f);
			BufferedReader br = new BufferedReader(reader);
			char[] cs = new char[1024];
			int len = -1;
			StringBuilder buf = new StringBuilder();
			while ((len = br.read(cs)) != -1) {
				buf.append(new String(cs, 0, len));
			}
			br.close();
			System.out.println(buf);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static void charWriter(String f) {
		try {
			Writer reader = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(reader);
			bw.write("我是你妈");
			bw.flush();
			bw.close();
			System.out.println("写入成功");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		//byteWreter();
		//byteReader("D:/新建 文本文档.txt");
		charWriter("D:/新建 文本文档.txt");
		charReader("D:/新建 文本文档.txt");
	}
	
}
