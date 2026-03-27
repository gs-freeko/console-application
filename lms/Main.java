package lms;

import lms.model.*;
import lms.service.*;
import lms.data.DataStore;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AuthService authService = new AuthService();
        BookService bookService = new BookService();

        System.out.println("===== Library Management System =====");
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        User user = authService.login(email, password);

        if (user == null) {
            System.out.println("Invalid credentials!");
            return;
        }

        // ================= ADMIN MENU =================
        if (user instanceof Admin) {

            boolean running = true;

            while (running) {
                System.out.println("\n--- Admin Menu ---");
                System.out.println("1. View Books");
                System.out.println("2. Add Book");
                System.out.println("3. Delete Book");
                System.out.println("0. Logout");

                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:
                        bookService.viewBooks();
                        break;

                    case 2:
                        System.out.print("Enter Book ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Book Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Author: ");
                        String author = sc.nextLine();

                        System.out.print("Enter Quantity: ");
                        int qty = sc.nextInt();

                        DataStore.getBooks().add(new Book(id, name, author, qty));
                        System.out.println("Book added successfully!");
                        break;

                    case 3:
                        System.out.print("Enter Book ID to delete: ");
                        int delId = sc.nextInt();

                        DataStore.getBooks().removeIf(b -> b.getId() == delId);
                        System.out.println("Book deleted successfully!");
                        break;

                    case 0:
                        running = false;
                        System.out.println("Logging out...");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }
            }
        }

        // ================= USER MENU =================
        else {

            boolean running = true;

            while (running) {
                System.out.println("\n--- User Menu ---");
                System.out.println("1. View Books");
                System.out.println("2. Search Book");
                System.out.println("3. Borrow Book");
                System.out.println("4. Return Book");
                System.out.println("5. View Borrowed Books");
                System.out.println("0. Logout");

                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:
                        bookService.viewBooks();
                        break;

                    case 2:
                        System.out.print("Enter book name: ");
                        String searchName = sc.nextLine();

                        Book found = bookService.searchBook(searchName);
                        if (found != null) {
                            System.out.println("Found: " + found.getName() + " by " + found.getAuthor());
                        } else {
                            System.out.println("Book not found!");
                        }
                        break;

                    case 3:
                        System.out.print("Enter Book ID to borrow: ");
                        int bid = sc.nextInt();

                        bookService.borrowBook(user, bid);
                        break;

                    case 4:
                        System.out.print("Enter Book ID to return: ");
                        int rid = sc.nextInt();

                        bookService.returnBook(user, rid);
                        break;

                    case 5:
                        bookService.viewBorrowedBooks(user);
                        break;

                    case 0:
                        running = false;
                        System.out.println("Logging out...");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }
            }
        }

        sc.close();
    }
}
