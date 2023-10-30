package com.autuo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

public class ChangeStreamDemo {

	private static void write(OutputStream out) {
		Writer writer = new OutputStreamWriter(out, Charset.forName("UTF-8"));
		try {
			writer.write("我爱你是多么清楚\r\n");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void read(InputStream in) {
		Reader reader = new InputStreamReader(in, Charset.forName("UTF-8"));
		char[] cs = new char[1024];
		int len = -1;
		try {
			while ((len = reader.read(cs)) != -1) {
				System.out.println(new String(cs, 0, len));
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		InputStream in;
		OutputStream out;
		try {
			in = new FileInputStream("D:/新建 文本文档.txt");
			read(in);
			out = new FileOutputStream("D:/新建 文本文档.txt", true);
			write(out);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
