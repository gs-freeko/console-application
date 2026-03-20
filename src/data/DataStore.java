package data;

import model.*;
import java.util.*;

public class DataStore {
    public static List<Book> books = new ArrayList<>();
    public static List<User> users = new ArrayList<>();

    static {
        users.add(new User("admin@gmail.com", "admin", "admin"));
        users.add(new User("user@gmail.com", "123", "borrower"));
    }
}
