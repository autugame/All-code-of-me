package com.autuo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsDemo {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("aam");
		list.add("away");
		list.add("aaa");
		//Collections.reverse(list);
//		Collections.shuffle(list);
//		Collections.sort(list);
//		Collections.swap(list, 0, 1);
//		Collections.rotate(list, 3);
//		System.out.println(Collections.binarySearch(list, "tom"));
		//System.out.println(Collections.max(list));
//		List<String> syncList = Collections.synchronizedList(new ArrayList<String>());
//		List<String> sList = Collections.emptyList();
//		sList.add("bin");
		Collections.sort(list, Collections.reverseOrder());
		list.forEach(System.out::println);
	}
	
	public static List<String> query() {
		List<String> list = null;
		return Collections.emptyList();
	}

}
