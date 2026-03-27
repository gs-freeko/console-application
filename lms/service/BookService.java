package lms.service;

import lms.model.Book;
import lms.model.User;
import lms.data.DataStore;

public class BookService {

    // ================= VIEW ALL BOOKS (TABLE FORMAT) =================
    public void viewBooks() {

        System.out.println("\n+----+----------------------+----------------------+----------+");
        System.out.printf("| %-2s | %-20s | %-20s | %-8s |\n", "ID", "Name", "Author", "Qty");
        System.out.println("+----+----------------------+----------------------+----------+");

        for (Book b : DataStore.getBooks()) {
            System.out.printf("| %-2d | %-20s | %-20s | %-8d |\n",
                    b.getId(),
                    truncate(b.getName(), 20),
                    truncate(b.getAuthor(), 20),
                    b.getQuantity());
        }

        System.out.println("+----+----------------------+----------------------+----------+");
    }

    // ================= SEARCH BOOK =================
    public Book searchBook(String name) {

        for (Book b : DataStore.getBooks()) {
            if (b.getName().equalsIgnoreCase(name)) {

                System.out.println("\n+----+----------------------+----------------------+----------+");
                System.out.printf("| %-2s | %-20s | %-20s | %-8s |\n", "ID", "Name", "Author", "Qty");
                System.out.println("+----+----------------------+----------------------+----------+");

                System.out.printf("| %-2d | %-20s | %-20s | %-8d |\n",
                        b.getId(),
                        truncate(b.getName(), 20),
                        truncate(b.getAuthor(), 20),
                        b.getQuantity());

                System.out.println("+----+----------------------+----------------------+----------+");

                return b;
            }
        }

        System.out.println("Book not found!");
        return null;
    }

    // ================= BORROW BOOK =================
    public void borrowBook(User user, int bookId) {

        for (Book b : DataStore.getBooks()) {
            if (b.getId() == bookId) {

                if (b.getQuantity() > 0) {
                    b.setQuantity(b.getQuantity() - 1);
                    user.getBorrowedBooks().add(b);

                    System.out.println("\n✅ Book borrowed successfully!");
                } else {
                    System.out.println("\n❌ Book not available!");
                }
                return;
            }
        }

        System.out.println("\n❌ Book not found!");
    }

    // ================= RETURN BOOK =================
    public void returnBook(User user, int bookId) {

        for (Book b : user.getBorrowedBooks()) {
            if (b.getId() == bookId) {

                b.setQuantity(b.getQuantity() + 1);
                user.getBorrowedBooks().remove(b);

                System.out.println("\n✅ Book returned successfully!");
                return;
            }
        }

        System.out.println("\n❌ You didn't borrow this book!");
    }

    // ================= VIEW BORROWED BOOKS =================
    public void viewBorrowedBooks(User user) {

        if (user.getBorrowedBooks().isEmpty()) {
            System.out.println("\nNo books borrowed.");
            return;
        }

        System.out.println("\n+----+----------------------+----------------------+");
        System.out.printf("| %-2s | %-20s | %-20s |\n", "ID", "Name", "Author");
        System.out.println("+----+----------------------+----------------------+");

        for (Book b : user.getBorrowedBooks()) {
            System.out.printf("| %-2d | %-20s | %-20s |\n",
                    b.getId(),
                    truncate(b.getName(), 20),
                    truncate(b.getAuthor(), 20));
        }

        System.out.println("+----+----------------------+----------------------+");
    }

    // ================= HELPER METHOD =================
    private String truncate(String str, int maxLength) {
        if (str == null) return "";
        if (str.length() <= maxLength) return str;
        return str.substring(0, maxLength - 3) + "...";
    }
}
