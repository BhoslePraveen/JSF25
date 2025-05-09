package com.smartlibrary;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Library implements Serializable{
	
	Map<String, Book> books = new HashMap<>();
	Map<String, User> users = new HashMap<>();

	private static final String FILE_PATH ="library.json";
	
		
	public void addBook(String BookId, String title, String author) {
		books.put(BookId, new Book(BookId, title, author));
		saveDataToFile();
	}

	public void registerUser(String userId, String Name) {
		users.put(userId, new User(Name, userId));
		saveDataToFile();
	}

	public void borrowBook(String userId, String BookId) {
		Book book = books.get(BookId);
		User user = users.get(userId);

		if (book != null && user != null && book.isAvailable) {
			book.isAvailable = false;
			user.borrowedBooks.add(book);
			saveDataToFile();
            System.out.println("Book borrowed successfully.");
		}else {
            System.out.println("Book not available or user not found.");
		}

	}

	public void returnBook(String userId, String BookId) {
		Book book = books.get(BookId);
		User user = users.get(userId);
		if (user != null && book != null && user.borrowedBooks.contains(book)) {
			book.isAvailable = true;
			user.borrowedBooks.remove(book);
			saveDataToFile();
            System.out.println("Book returned successfully.");
		}
		else {
            System.out.println("Invalid user or book.");
		}

	}

	public List<Book> getBorrowedBooks(String userId) {
		User user = users.get(userId);
		loadDataToFile();
		return (user != null) ? user.borrowedBooks : new ArrayList<Book>();

	}

	public void saveDataToFile() {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(this);
			Files.write(Paths.get(FILE_PATH), json.getBytes());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Library loadDataToFile() {
		try {
			String json = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
			Gson gson = new Gson();
			return gson.fromJson(json, Library.class);
		} catch (IOException e) {
			return new Library();
		}
				
	}

	public static void main(String[] args) {
//		Book books = new Book();
//		User users = new User();
		Library library = loadDataToFile();
		while (true) {
			System.out.println("Enter a choice");
			System.out.println("1. AddBook ");
			System.out.println("2. Add user");
			System.out.println("3. Borrow books");
			System.out.println("4. Return Book");
			System.out.println("5. display borrowed books");
			System.out.println("6. Exit");
			Scanner sc = new Scanner(System.in);
			int num = sc.nextInt();
			switch (num) {
			case 1:
				System.out.println("Enter bookid, title, author");
				sc.nextLine(); // consume the leftover newline
				String BookId = sc.nextLine();
				String title = sc.nextLine();
				String author = sc.nextLine();

				library.addBook(BookId, title, author);
				break;

			case 2:
				System.out.println("Add user details userid and name");
				sc.nextLine(); // consume the leftover newline
				String userId = sc.nextLine();
				String Name = sc.nextLine();
				library.registerUser(userId, Name);
				break;

			case 3:
				System.out.println("To borrow book enter userid and book");
				sc.nextLine(); // consume the leftover newline
				String u2 = sc.nextLine();
				String b2 = sc.nextLine();
				library.borrowBook(u2, b2);
				break;

			case 4:
				System.out.println("To return book enter userid and book");
				sc.nextLine(); // consume the leftover newline
				String u1 = sc.nextLine();
				String b1 = sc.nextLine();
				library.returnBook(u1, b1);
				break;

			case 5:
				System.out.println("To display borrowed book enter userId ");
				sc.nextLine(); // consume newline
				String u = sc.nextLine();
				List<Book> borrowed = library.getBorrowedBooks(u);
				if (borrowed.isEmpty()) {
					System.out.println("No books borrowed.");
				} else {
					for (Book b : borrowed) {
						System.out.println("-> " + b.title + " by " + b.author);
					}
				}
				break;

			case 6:
				System.exit(0);
			default:
				System.out.println("Worng Choise");

			}

		}

	}
}
