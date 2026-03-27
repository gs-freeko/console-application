package lms.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    protected String email;
    protected String password;
    private List<Book> borrowedBooks;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}