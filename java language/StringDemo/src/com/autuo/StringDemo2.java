package com.autuo;

import java.util.Arrays;

public class StringDemo2 {
	public static void main(String[] args) {
		String str = " a jdjwidjsakw111111111 ";
		char c = str.charAt(5);
		System.out.println(c);
		System.out.println(str.toCharArray());
		char[] d = str.toCharArray();
		for (int i : d) {
			System.out.println(i);
		}
		char[] cs = {'a', 'b', 'c'};
		String s1 = new String(cs);
		System.out.println(s1);
		String s2 = new String(cs, 0, 1);
		System.out.println(s2);
		
		System.out.println(Arrays.toString(str.getBytes()));
		System.out.println(str.replace('w', '*'));
		System.out.println(str.replaceFirst("\\d", "*"));;
		System.out.println(str.substring(0, 4));
		System.out.println(Arrays.toString(str.split("d")));
		System.out.println(str.contains("1"));
		System.out.println(str.indexOf("a"));
		System.out.println(str.lastIndexOf("a"));
		System.out.println(str.isEmpty());
		System.out.println(str.length());
		System.out.println(str.trim());
		System.out.println(str.concat("****"));
		System.out.println(String.valueOf('a'));
	}
}
