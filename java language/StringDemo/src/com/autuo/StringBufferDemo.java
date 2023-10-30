package com.autuo;

public class StringBufferDemo {

	public static void main(String[] args) {
		String a = "a";
		String b = "b";
		String c = a + b + 1;
		System.out.println(c);
		
		String d = "a" + 1 + 2 + 3 + 4 + "b";
		StringBuffer sb = new StringBuffer(32);//带容量的构造建议用法
		sb.append(a).append(b).append(1);
		System.out.println(sb.toString());
		StringBuffer sb2 = new StringBuffer("abc");
		System.out.println(sb2);
	}

}
