package com.autuo;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * 程序国际化
 * 
 * @company
 * @author autuo
 * @description
 */
public class I18NDemo {

	public static void main(String[] args) {
		// 创建一个本地语言环境对象
		Locale locale_CN = new Locale("zh", "CN");
		Locale locale_US = new Locale("en", "US");
		Locale locale_default = Locale.getDefault();
		Scanner input = new Scanner(System.in);
		ResourceBundle r = ResourceBundle.getBundle("com.autuo.info");
		System.out.println(r.getString("system.name"));
		System.out.println(r.getString("input.username"));
		String username = input.nextLine();
		System.out.println(r.getString("input.password"));
		String password = input.nextLine();
		if ("admin".equals(username) && "123".equals(password)) {
			System.out.println(r.getString("login.success"));
			String welcome = r.getString("welcome");
			welcome = MessageFormat.format(welcome, username);
			System.out.println(welcome);
		} else {
			System.out.println(r.getString("login.error"));
		}
	}

}
