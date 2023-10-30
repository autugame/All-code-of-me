package com.autuo;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class QueueDequeDemo {
	
	private static void stack() {
		Stack<String> s = new Stack<>();
		s.push("bin");
		s.push("lili");
		s.push("tom");
		System.out.println(s.pop());
		System.out.println(s.pop());
	}
	
	private static void deque() {
		Deque<String> deque = new LinkedList<>();
		deque.add("小花");
		deque.add("小白");
		deque.add("小离");
		deque.add("小哎");
		deque.add("小v");
		deque.add("小晓");
		System.out.println(deque.getFirst());
		System.out.println(deque.getLast());
	}
	
	private static void queue() {
		Queue<String> queue = new LinkedList<>();
		queue.add("小花");
		queue.add("小白");
		queue.add("小离");
		queue.add("小哎");
		queue.add("小v");
		queue.add("小晓");
		System.out.println(queue.size());
		System.out.println(queue.peek());
		System.out.println(queue.size());
		System.out.println(queue.poll());
		System.out.println(queue.size());
	}

	public static void main(String[] args) {
		//queue();
		//deque();
		stack();
	}

}
