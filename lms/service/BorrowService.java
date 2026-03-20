package service;

import model.*;

public class BorrowService {

    public static void borrowBook(User user, Book book) {

        if (user.getBorrowedBooks().size() >= 3) {
            System.out.println("Max 3 books allowed!");
            return;
        }

        if (book.getQuantity() <= 0) {
            System.out.println("Book not available!");
            return;
        }

        user.getBorrowedBooks().add(book);
        book.setQuantity(book.getQuantity() - 1);

        System.out.println("Book borrowed successfully!");
    }

    public static void returnBook(User user, String isbn) {

        for (Book b : user.getBorrowedBooks()) {
            if (b.getIsbn().equals(isbn)) {
                b.setQuantity(b.getQuantity() + 1);
                user.getBorrowedBooks().remove(b);
                System.out.println("Returned successfully!");
                return;
            }
        }

        System.out.println("Book not found in your list!");
    }
}
