package spm;

import spm.model.*;
import spm.service.*;
import spm.data.DataStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AuthService auth = new AuthService();
        InventoryService inv = new InventoryService();
        CustomerService custService = new CustomerService();
        PaymentService payService = new PaymentService();
        ReportService rptService = new ReportService();

        System.out.println("=== Welcome to Supermarket Billing System ===");
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        User user = auth.login(email, password);
        if (user == null) {
            System.out.println("Invalid login!");
            return;
        }

        // -------------------- ADMIN MENU --------------------
        if (user instanceof Admin) {
            Admin admin = (Admin) user;
            boolean running = true;
            while (running) {
                System.out.println("\n--- Admin Menu ---");
                System.out.println("1. Add Product");
                System.out.println("2. Modify Product Quantity");
                System.out.println("3. Delete Product");
                System.out.println("4. List Products by Name");
                System.out.println("5. List Products by Price");
                System.out.println("6. Search Product");
                System.out.println("7. Reports");
                System.out.println("8. Add Customer/Admin");
                System.out.println("0. Logout");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Product ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Product Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Price: ");
                        double price = sc.nextDouble();
                        System.out.print("Enter Quantity: ");
                        int qty = sc.nextInt();
                        inv.addProduct(new Product(id, name, price, qty));
                        System.out.println("Product added successfully!");
                        break;

                    case 2:
                        System.out.print("Enter Product ID to modify: ");
                        int mid = sc.nextInt();
                        System.out.print("Enter new quantity: ");
                        int newQty = sc.nextInt();
                        inv.modifyProductQuantity(mid, newQty);
                        System.out.println("Quantity updated!");
                        break;

                    case 3:
                        System.out.print("Enter Product ID to delete: ");
                        int delId = sc.nextInt();
                        inv.deleteProduct(delId);
                        System.out.println("Product deleted!");
                        break;

                    case 4:
                        System.out.println("Products by Name:");
                        inv.listProductsByName()
                                .forEach(p -> System.out.println(p.getId() + " " + p.getName() + " Rs " + p.getPrice() + " Qty: " + p.getQuantity()));
                        break;

                    case 5:
                        System.out.println("Products by Price:");
                        inv.listProductsByPrice()
                                .forEach(p -> System.out.println(p.getId() + " " + p.getName() + " Rs " + p.getPrice() + " Qty: " + p.getQuantity()));
                        break;

                    case 6:
                        System.out.print("Enter product name to search: ");
                        String searchName = sc.nextLine();
                        Product prod = inv.searchProduct(searchName);
                        if (prod != null) {
                            System.out.println("Found: " + prod.getName() + " - Rs " + prod.getPrice() + " - Qty: " + prod.getQuantity());
                        } else {
                            System.out.println("Product not found!");
                        }
                        break;

                    case 7:
                        System.out.println("\n--- Reports ---");
                        rptService.lowStockProducts(20);
                        rptService.neverPurchasedProducts();
                        rptService.topCustomers();
                        break;

                    case 8:
                        System.out.print("Add (1) Customer or (2) Admin? Enter 1 or 2: ");
                        int type = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Email: ");
                        String newEmail = sc.nextLine();
                        System.out.print("Enter Password: ");
                        String newPass = sc.nextLine();
                        if (type == 1) {
                            DataStore.getCustomers().add(new Customer(newEmail, newPass));
                            System.out.println("Customer added successfully!");
                        } else if (type == 2) {
                            DataStore.getAdmins().add(new Admin(newEmail, newPass));
                            System.out.println("Admin added successfully!");
                        } else {
                            System.out.println("Invalid type!");
                        }
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

        // -------------------- CUSTOMER MENU --------------------
        else if (user instanceof Customer) {
            Customer customer = (Customer) user;
            boolean running = true;
            List<CartItem> cart = new ArrayList<>();

            while (running) {
                System.out.println("\n--- Customer Menu ---");
                System.out.println("1. View Products");
                System.out.println("2. Add Product to Cart");
                System.out.println("3. Edit Cart");
                System.out.println("4. Remove Item from Cart");
                System.out.println("5. Checkout / Payment");
                System.out.println("6. View Purchase History");
                System.out.println("0. Logout");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.println("Available Products:");
                        DataStore.getProducts().forEach(p ->
                                System.out.println(p.getId() + " " + p.getName() + " Rs " + p.getPrice() + " Qty: " + p.getQuantity()));
                        break;

                    case 2:
                        System.out.print("Enter Product ID to add: ");
                        int pid = sc.nextInt();
                        System.out.print("Enter Quantity: ");
                        int pq = sc.nextInt();
                        sc.nextLine();
                        Product pObj = inv.searchProductById(pid);
                        if (pObj != null && pq <= pObj.getQuantity()) {
                            cart.add(new CartItem(pObj, pq));
                            System.out.println("Added to cart!");
                        } else {
                            System.out.println("Invalid product or insufficient stock.");
                        }
                        break;

                    case 3:
                        System.out.print("Enter Product ID to update in cart: ");
                        int upid = sc.nextInt();
                        System.out.print("Enter new quantity: ");
                        int newQ = sc.nextInt();
                        sc.nextLine();
                        boolean found = false;
                        for (CartItem c : cart) {
                            if (c.getProduct().getId() == upid) {
                                c.setQuantity(newQ);
                                found = true;
                                break;
                            }
                        }
                        System.out.println(found ? "Cart updated!" : "Item not found in cart.");
                        break;

                    case 4:
                        System.out.print("Enter Product ID to remove from cart: ");
                        int rid = sc.nextInt();
                        sc.nextLine();
                        cart.removeIf(c -> c.getProduct().getId() == rid);
                        System.out.println("Removed if existed.");
                        break;

                    case 5:
                        Bill bill = payService.processPayment(customer, new ArrayList<>(cart));
                        if (bill != null) {
                            System.out.println("Bill Number: " + bill.getBillNumber());
                            System.out.println("Total Amount: Rs " + bill.getTotalAmount());
                            cart.clear();
                        }
                        break;

                    case 6:
                        System.out.println("Purchase History:");
                        for (Bill b : customer.getPurchaseHistory()) {
                            System.out.println("Bill " + b.getBillNumber() + " Date: " + b.getBillDate() + " Total: Rs " + b.getTotalAmount());
                            b.getItems().forEach(item ->
                                    System.out.println(item.getProduct().getName() + " - Qty: " + item.getQuantity() + " Rs " + item.getProduct().getPrice()));
                        }
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