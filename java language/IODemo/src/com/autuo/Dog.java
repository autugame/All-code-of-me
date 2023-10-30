package com.autuo;

import java.io.Serializable;

public class Dog implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private String name;
	private int age;
	private String sex;
	@SuppressWarnings("unused")
	private transient int id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Dog(String name, int age, String sex) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
	}
	public Dog() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Dog [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
	
}
