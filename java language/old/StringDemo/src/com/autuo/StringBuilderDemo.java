package com.autuo;

public class StringBuilderDemo {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		String a = "a" + 1;
		String b = a + "b";
		String c = null;
		for (int i = 0; i < 5; i++) {
			c += i;
		}
		System.out.println(c);
	}
}
