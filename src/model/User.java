package model;

import java.util.*;

public class User {
    private String email;
    private String password;
    private String role;

    private List<Book> borrowedBooks = new ArrayList<>();

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }
}