package com.smartlibrary;

import java.io.Serializable;

public class Book implements Serializable{
	String bookId;
	String title;
	String author;
	public boolean isAvailable= true;
	
	public Book() {}

	public Book(String bookId, String title, String author) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.isAvailable = true;
	}
		
}
