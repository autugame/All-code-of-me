package com.autuo;
/**
 * 
 * @company
 * @author autuo
 * @description
 */
public class StringDemo {

	public static void main(String[] args) {
		String s1 = "愤愤";
		String s2 = new String("愤愤");
		System.out.println(s1 + s2);
		String s3 = "纷纷";
		String a = "a";
		//变量在运行期才会被确定
		String a1 = "a" + 1;
		String a2 = "a1";
		System.out.println(a1 == a2);
		System.out.println(a1);
		
		final String b = "b";
		String c = getString();
		String c1 = c + "1";
		String c2 = "c1";
		System.out.println(c1 == c2);
		
		final String d = getD();
	}
	
	private static String getString() {
		return "c";
	}
	
	private static String getD() {
		return "d";
	}
}
