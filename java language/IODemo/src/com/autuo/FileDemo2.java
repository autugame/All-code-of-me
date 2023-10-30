package com.autuo;

import java.io.File;

public class FileDemo2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		findFile(new File("D:\\Program Files\\360"), ".jpg");
	}
	
	private static void findFile(File target, String ext) {
		if (target == null)
			return;
		//如果文件是目录
		if (target.isDirectory()) {
			File[] files = target.listFiles();
			if (files != null) {
				for (File f : files) {
					findFile(f, ext);
				}
			}
		} else {
			String name = target.getName().toLowerCase();
			//System.out.println(name);
			if (name.endsWith(ext)) {
				System.out.println(target.getAbsolutePath());
			}
		}
	}

}
