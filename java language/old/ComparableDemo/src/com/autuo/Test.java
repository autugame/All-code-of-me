package com.autuo;

import java.util.Arrays;

public class Test {
	
	public static void main(String[] args) {
		int[] nums = { 34, 35, 36, 37, 2938, 81203498, 183471 };
		Arrays.sort(nums);
		System.out.println(Arrays.toString(nums));
		
		String[] names = { "jack", "shao", "name", "ooasd" };
		Arrays.sort(names);
		System.out.println(Arrays.toString(names));
		Cat[] cats = { new Cat("fenfen", 1), new Cat("feifei", 3), new Cat("nienie", 3) };
		Arrays.sort(cats);
		System.out.println(Arrays.toString(cats));
		Dog[] dogs = { new Dog("fenfen", 1), new Dog("feifei", 3), new Dog("nienie", 3) };
		Arrays.sort(dogs, new DogComparator());
		System.out.println(Arrays.toString(dogs));
	}
	
}
