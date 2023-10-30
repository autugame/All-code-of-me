package com.autuo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class ListDemo {
	
	private static void linkedList() {
		LinkedList<String> list = new LinkedList<>();
		list.add("宫本");
		list.add("美女");
		int size = list.size();
		for (int i = 0; i < size; i++) {
			System.out.println(list.get(i));
		}
	}
	
	private static void vector() {
		Vector<String> v = new Vector<>();
		v.add("宫本");
		v.add("美女");
		
		for (String str:v) {
			System.out.println(str);
		}
	}

	private static void arrayList() {
		List<String> list = new ArrayList<String>();
		list.add("宫本");
		list.add("美女");
		int size = list.size();
		for (int i = 0; i < size; i++) {
			System.out.println(list.get(i));
		}
		System.out.println(list.contains("宫本"));
	}

	public static void main(String[] args) {
		//arrayList();
		//vector();
		linkedList();
	}

}
