package lms.data;

import lms.model.Book;
import lms.model.Admin;
import lms.model.User;

import java.util.ArrayList;
import java.util.List;

public class DataStore {

    private static List<Book> books = new ArrayList<>();
    private static List<Admin> admins = new ArrayList<>();
    private static List<User> users = new ArrayList<>();

    static {
        // Default Admin
        admins.add(new Admin("admin@gmail.com", "admin123"));

        // Default User
        users.add(new User("user@gmail.com", "user123"));

        // Sample Books
        books.add(new Book(1, "Java", "James Gosling", 5));
        books.add(new Book(2, "DBMS", "Korth", 3));
        books.add(new Book(3, "OS", "Galvin", 2));
    }

    public static List<Book> getBooks() {
        return books;
    }

    public static List<Admin> getAdmins() {
        return admins;
    }

    public static List<User> getUsers() {
        return users;
    }
}
