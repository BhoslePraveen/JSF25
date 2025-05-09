package com.smartlibrary;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    public String userId;
    public String name;
    public List<Book> borrowedBooks = new ArrayList<>();

    public User() {}
    
    public User(String userId, String name) {
        this.name = name;
        this.userId = userId;
    }
    
}
