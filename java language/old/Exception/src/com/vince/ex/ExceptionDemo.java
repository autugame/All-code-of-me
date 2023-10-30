package com.vince.ex;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 异常处理
 * 
 * @company
 * @author autuo
 * @description
 */
public class ExceptionDemo {

	public static void main(String[] args) {
		div(10, 0);
		method();
		try {
			div2(20, 0);
		} catch (Exception e) {
			System.out.println("程序异常");
		}
		System.out.println("运行结束");
		input();
	}

	/**
	 * 除法运算
	 * 
	 * @param num1
	 * @param num2
	 */
	private static void div(int num1, int num2) {
		int[] arr = { 1, 2, 3, 4, 5 };
		try {
			System.out.println(num1 / num2);
			System.out.println(arr[4]);
			arr = null;
			System.out.println(arr[4]);
			int result = num1 / num2;
			System.out.println("result=" + result);
		} catch (ArithmeticException e) {
			System.out.println("尊敬的用户除数不能为零");
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("数组下标越界");
		} catch (NullPointerException e) {
			System.out.println("空指针异常");
		} catch (Exception e) {
			System.out.println("出错了");
		} finally {
			System.out.println("运行结束");
		}
	}

	private static int method() {
		int a = 10;
		int b = 5;
		try {
			System.out.println(a);
			System.out.println(b);
			int c = a / b;
			System.out.println(c);
			return c;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("finally.");
		}
		return 1;
	}

	private static int div2(int a, int b) throws ArithmeticException {
		try {
			int c = a / b;
			return c;
		} catch (ArithmeticException e) {
			throw new ArithmeticException("除数不能为零");
		}
	}

	private static void input() {
		Scanner input = new Scanner(System.in);
		try {
			int num = input.nextInt();
			System.out.println(num);
		} catch (InputMismatchException e) {
			System.out.println("输入不匹配");
		}
	}

}
