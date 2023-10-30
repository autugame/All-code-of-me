package com.autuo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

public class FileDivisionMergeDemo {

	private static void division(File targetFile, long cutSize) {
		if (targetFile == null) return;
		//计算总分割文件数
		int num = targetFile.length() % cutSize == 0 ? (int) (targetFile.length() / cutSize) : (int) (targetFile.length() / cutSize + 1);
		
		//构造一个文件输入流
		try {	
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(targetFile));
			BufferedOutputStream out = null;
			
			byte[] bytes = null;//每次读取字节数
			int len = -1;
			int count = 0;//每一个文件读取的次数

			for (int i = 0; i < num; i++) {
				out = new BufferedOutputStream(new FileOutputStream(new File("D:\\test\\" + (i + 1) + "-temp" + targetFile.getName())));
				
				if (cutSize <= 1024) {
					bytes = new byte[(int) cutSize];
					count = 1;
				} else {
					bytes = new byte[1024];
					count = (int) cutSize / 1024;
				}
				
				while (count > 0 && (len = in.read(bytes)) != -1) {
					out.write(bytes, 0, len);
					out.flush();
					count--;
				}
				if (cutSize % 1024 != 0) {
					bytes = new byte[(int) cutSize % 1024];
					len = in.read(bytes);
					out.write(bytes, 0, len);
					out.flush();
					out.close();
				}
			}
			in.close();
			System.out.println("文件分割完成");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void merge(Enumeration<InputStream> es) {
		SequenceInputStream sis = new SequenceInputStream(es);
		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("D:\\test\\autuo.mp4"));
			byte[] bytes = new byte[1024];
			int len = -1;
			while ((len = sis.read(bytes)) != -1) {
				bos.write(bytes, 0, len);
				bos.flush();
			}
			bos.close();
			sis.close();
			System.out.println("合并完成");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		File file = new File("D:\\autuo.mp4");
//		division(file, 1024 * 1024);
		try {
			InputStream in1 = new FileInputStream(new File("D:\\test\\1-tempautuo.mp4"));
			InputStream in2 = new FileInputStream(new File("D:\\test\\2-tempautuo.mp4"));
			InputStream in3 = new FileInputStream(new File("D:\\test\\3-tempautuo.mp4"));
			InputStream in4 = new FileInputStream(new File("D:\\test\\4-tempautuo.mp4"));
			InputStream in5 = new FileInputStream(new File("D:\\test\\5-tempautuo.mp4"));
			InputStream in6 = new FileInputStream(new File("D:\\test\\6-tempautuo.mp4"));
			InputStream in7 = new FileInputStream(new File("D:\\test\\7-tempautuo.mp4"));
			InputStream in8 = new FileInputStream(new File("D:\\test\\8-tempautuo.mp4"));
			InputStream in9 = new FileInputStream(new File("D:\\test\\9-tempautuo.mp4"));
			InputStream in10 = new FileInputStream(new File("D:\\test\\10-tempautuo.mp4"));
			InputStream in11 = new FileInputStream(new File("D:\\test\\11-tempautuo.mp4"));			
			Vector<InputStream> v = new Vector<InputStream>();
			v.add(in1);
			v.add(in2);
			v.add(in3);
			v.add(in4);
			v.add(in5);
			v.add(in6);
			v.add(in7);
			v.add(in8);
			v.add(in9);
			v.add(in10);
			v.add(in11);
			Enumeration<InputStream> es = v.elements();
			merge(es);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
