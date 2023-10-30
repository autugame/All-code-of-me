package com.autuo;

import java.util.Random;
import static java.lang.Math.floor;

public class MathRandomDemo {

	public static void main(String[] args) {
		System.out.println(Math.PI);
		System.out.println(Math.abs(-10));
		System.out.println(Math.random() * 100);
		System.out.println(Math.round(Math.random() * 100) / 100.0);
		System.out.println(Math.sqrt(4));
		System.out.println(floor(1.23456));
		Random r = new Random();
		System.out.println(r.nextInt());
	}

}
