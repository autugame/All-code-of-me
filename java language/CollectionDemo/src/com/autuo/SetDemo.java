package com.autuo;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.LinkedHashSet;

public class SetDemo {
	
	private static void LinkedHashSet() {
		LinkedHashSet<Cat> set = new LinkedHashSet<>();
		Cat c1 = new Cat("喵喵", 5, 1);
		Cat c2 = new Cat("花花", 2, 2);
		Cat c3 = new Cat("汤姆", 4, 3);
		Cat c4 = new Cat("喵喵", 5, 1);
		set.add(c1);
		set.add(c2);
		set.add(c3);
		set.add(c4);
		for (Cat c : set) {
			System.out.println(c);
		}
	}
	
	private static void TreeSet() {
		Cat c1 = new Cat("喵喵", 5, 1);
		Cat c2 = new Cat("花花", 2, 2);
		Cat c3 = new Cat("汤姆", 4, 3);
		Cat c4 = new Cat("喵喵", 5, 1);
		TreeSet<Cat> tree = new TreeSet<>(new CatComparator());
		tree.add(c1);
		tree.add(c2);
		tree.add(c3);
		tree.add(c4);
		System.out.println(tree.size());
		for (Cat c : tree) {
			System.out.println(c);
		}
	}

	private static void hashSet() {
		Set<String> set = new HashSet<>();
		set.add("小米");
		set.add("华为");
		set.add("vivo");
		set.add("oppe");

		String[] names = set.toArray(new String[] {});
		for (String s : names) {
			System.out.println(s);
		}

		Cat c1 = new Cat("喵喵", 5, 1);
		Cat c2 = new Cat("花花", 2, 2);
		Cat c3 = new Cat("汤姆", 4, 3);
		Cat c4 = new Cat("喵喵", 5, 1);
		Set<Cat> cats = new HashSet<Cat>();
		cats.add(c1);
		cats.add(c2);
		cats.add(c3);
		cats.add(c4);

		for (Cat c : cats) {
			System.out.println(c);
		}

		System.out.println(cats.size());
	}

	public static void main(String[] args) {
		//hashSet();
		//TreeSet();
		LinkedHashSet();
	}

}
