package com.example.demo.service;

public interface Sayable {
	default String say(){  
		System.out.println("Hello, this is default method"); 
		return "Default method executed";
	}  
	
	public static int add(int a, int b) {
        return a + b;
    }
	void sayMore(String msg); 
}
