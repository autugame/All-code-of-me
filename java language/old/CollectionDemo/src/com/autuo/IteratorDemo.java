package com.autuo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class IteratorDemo {
	
	private static void predicateTest() {
		List<String> list = Arrays.asList("autuo", "baicai", "xiaoji", "jianren", "ccods;kdjf");
		List<String> result = filter(list, (s) -> s.contains("a"));
		result.forEach(System.out::println);
	}
	
	private static List<String> filter(List<String> list, Predicate<String> p) {
		List<String> result = new ArrayList<>();
		for (String s : list) {
			if (p.test(s)) {
				result.add(s);
			}
		}
		return result;
	}
	
	private static void supplierTest() {
		List<Integer> list = getNums(10, () -> (int)(Math.random() * 100));
		list.forEach(System.out::println);
	}
	
	private static List<Integer> getNums(int num, Supplier<Integer> sup) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			list.add(sup.get());
		}
		return list;
	}
	
	private static void functionTest() {
		String s = strToUpp("autuo", (str) -> str.toUpperCase());
		System.out.println(s);
	}
	
	public static String strToUpp(String str, Function<String, String> f) {
		return f.apply(str);
	}
	
	private static void foreach() {
		List<String> list = new ArrayList<>();
		list.add("job");
		list.add("tom");
		list.forEach(s -> System.out.println(s));
		list.forEach(System.out::println);
	}
	
	private static void enumration() {
		Vector<String> vs = new Vector<>();
		vs.add("job");
		vs.add("tom");
		
		Enumeration<String> es = vs.elements();
		while (es.hasMoreElements()) {
			System.out.println(es.nextElement());
		}
	}
	
	private static void iterator1(Collection<Cat> c) {
		for (Cat cat : c) {
			System.out.println(cat);
		}
	}
	private static void iterator2(Collection<Cat> c) {
		Iterator<Cat> iter = c.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

	public static void main(String[] args) {
		List<Cat> list = new ArrayList<>();
		Cat c1 = new Cat("喵喵", 5, 1);
		Cat c2 = new Cat("花花", 2, 2);
		Cat c3 = new Cat("汤姆", 4, 3);
		Cat c4 = new Cat("喵喵", 5, 1);
		list.add(c1);
		list.add(c2);
		list.add(c3);
		list.add(c4);
		//iterator1(list);
		//iterator2(list);
		//enumration();
		//foreach();
		//functionTest();
		//supplierTest();
		predicateTest();
	}

}
