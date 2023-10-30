package com.autuo;

import java.util.HashMap;
import java.util.Map;

public class MapNewMethodDemo {

	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "jeck");
		map.put(2, "tom");
		map.put(3, "lili");
		
		//String value = map.getOrDefault(4, "null");
		//System.out.println(value);
		
		//map.putIfAbsent(1, value);
		
//		map.put(3, "vince");
//		map.forEach((key, value) -> System.out.println(key + "->" + value));
//		String val = map.putIfAbsent(3, "mangmang");
//		System.out.println(val);
		//System.out.println(map.remove(1, "lili"));
		//map.replace(1, "ikun");
		//map.replace(1, "ikun", "smallbreaksun");
		//map.compute(1, (k, v1) -> v1 + "1");
		//map.computeIfAbsent(5, (k1) -> k1 + "min");
		map.merge(1, "000", (v1, v2) -> v1.concat(v2));
		
		map.forEach((key, value) -> System.out.println(key + "->" + value));
	}

}
