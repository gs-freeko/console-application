package spm;

import java.util.Scanner;
import spm.model.*;
import spm.service.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AuthService auth = new AuthService();
        InventoryService inv = new InventoryService();
        CustomerService cs = new CustomerService();
        PaymentService pay = new PaymentService();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String pass = sc.nextLine();

        User user = auth.login(email, pass);

        if (user == null) {
            System.out.println("Invalid login!");
            return;
        }

        if (user instanceof Admin) {

            while (true) {
                System.out.println("\n1.View 2.Add 3.Delete 0.Exit");
                int ch = sc.nextInt();

                switch (ch) {
                    case 1 -> inv.viewProducts();
                    case 2 -> {
                        System.out.print("ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Name: ");
                        String name = sc.nextLine();

                        System.out.print("Price: ");
                        double price = sc.nextDouble();

                        System.out.print("Qty: ");
                        int qty = sc.nextInt();

                        inv.addProduct(id, name, price, qty);
                    }
                    case 3 -> {
                        int id = sc.nextInt();
                        inv.deleteProduct(id);
                    }
                    case 0 -> { return; }
                }
            }
        }

        else if (user instanceof Customer c) {

            while (true) {
                System.out.println("\n1.View 2.AddCart 3.Cart 4.Pay 0.Exit");
                int ch = sc.nextInt();

                switch (ch) {
                    case 1 -> inv.viewProducts();
                    case 2 -> {
                        int id = sc.nextInt();
                        int qty = sc.nextInt();
                        cs.addToCart(c, id, qty);
                    }
                    case 3 -> cs.viewCart(c);
                    case 4 -> pay.checkout(c);
                    case 0 -> { return; }
                }
            }
        }
    }
}