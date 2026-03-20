import java.util.*;
import model.*;
import service.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.print("\nEmail: ");
            String email = sc.next();
            System.out.print("Password: ");
            String pass = sc.next();

            User user = AuthService.login(email, pass);

            if (user == null) {
                System.out.println("Invalid login!");
                continue;
            }

            if (user.getRole().equals("admin")) adminMenu();
            else borrowerMenu(user);
        }
    }

    static void adminMenu() {
        while (true) {
            System.out.println("\n1.Add Book 2.View 3.Delete 4.Logout");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> {
                    System.out.print("Name: ");
                    String name = sc.next();
                    System.out.print("ISBN: ");
                    String isbn = sc.next();
                    System.out.print("Qty: ");
                    int qty = sc.nextInt();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();

                    BookService.addBook(new Book(name, isbn, qty, price));
                }
                case 2 -> BookService.viewBooks();
                case 3 -> {
                    System.out.print("ISBN: ");
                    BookService.deleteBook(sc.next());
                }
                case 4 -> { return; }
            }
        }
    }

    static void borrowerMenu(User user) {
        while (true) {
            System.out.println("\n1.View 2.Borrow 3.Return 4.Logout");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> BookService.viewBooks();
                case 2 -> {
                    System.out.print("ISBN: ");
                    Book b = BookService.findByISBN(sc.next());
                    if (b != null) BorrowService.borrowBook(user, b);
                }
                case 3 -> {
                    System.out.print("ISBN: ");
                    BorrowService.returnBook(user, sc.next());
                }
                case 4 -> { return; }
            }
        }
    }
}
