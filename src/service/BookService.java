package service;

import data.DataStore;
import model.Book;

public class BookService {

    public static void addBook(Book b) {
        DataStore.books.add(b);
        System.out.println("Book added successfully!");
    }

    public static void viewBooks() {
        if (DataStore.books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        for (Book b : DataStore.books) {
            System.out.println(b.getName() + " | " + b.getIsbn() + " | Qty: " + b.getQuantity());
        }
    }

    public static Book findByISBN(String isbn) {
        for (Book b : DataStore.books) {
            if (b.getIsbn().equals(isbn)) {
                return b;
            }
        }
        return null;
    }

    public static void deleteBook(String isbn) {
        boolean removed = DataStore.books.removeIf(b -> b.getIsbn().equals(isbn));
        System.out.println(removed ? "Book deleted!" : "Book not found!");
    }
}
