package com.vince.ex;
/**
 * 自定义异常通常都是通过继承一个异常类来实现
 * 1,Throwable
 * 2,exception
 * 3,runtimeexception
 * @company
 * @author autuo
 * @description
 */
public class MyException extends Exception {
	public MyException() {
		super();
	}
	public MyException(String message) {
		super(message);
	}
}
