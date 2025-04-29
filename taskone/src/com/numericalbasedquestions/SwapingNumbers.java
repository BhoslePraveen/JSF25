package com.numericalbasedquestions;

public class SwapingNumbers {

	public static void main(String[] args) {

		// Write a program to swap the two numbers using three variables.

		int x = 10, y = 20;
		System.out.println("Before swap x= " + x + " y=" + y);
		int temp = x;
		x = y;
		y = temp;
		System.out.println("Aftre swap x= " + x + " y=" + y);
		
		// Write a program to swap the two numbers without using three variables.

		System.out.println("Before swap x= " + x + " y=" + y);
		x = x + y;
		y = x - y;
		x = x - y;
		System.out.println("Swap with 2 variables Aftre swap x= " + x + " y=" + y);
		
		System.out.println("end of code");

	}

}
