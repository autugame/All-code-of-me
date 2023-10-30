package com.autuo;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;

public class PrintStreamDemo {
	
	private static void bytePrint() {
		File file = new File("D:/新建 文本文档.txt");
		OutputStream out;
		try {
			out = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(out);
			PrintStream ps = new PrintStream(bos);
			ps.println("我喜欢你");
			ps.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static void charPrint() {
		File file = new File("D:/新建 文本文档.txt");
		Writer out;
		try {
			out = new FileWriter(file);
			BufferedWriter bos = new BufferedWriter(out);
			PrintWriter ps = new PrintWriter(bos);
			ps.println("我喜欢你o");
			ps.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public static void main(String[] args) {
		bytePrint();
		charPrint();
	}
	
}
