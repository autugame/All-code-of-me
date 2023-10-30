package com.autuo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFileDemo {
	
	public static void main(String[] args) {
		copy("D:\\QT\\MaintenanceTool.exe", "D:\\下载\\MaintenanceTool.exe");
	}
	
	private static void copy(String src, String target) {
		File srcFile = new File(src);
		File targetFile = new File(target);
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(srcFile);
			out = new FileOutputStream(targetFile);
			
			byte[] bytes = new byte[1024];
			int len = -1;
			while ((len = in.read(bytes)) != -1) {
				out.write(bytes, 0, len);
			}
			System.out.println("复制成功");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
				if (out != null)
					out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
