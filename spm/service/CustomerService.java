package spm.service;

import spm.model.*;

public class CustomerService {

    InventoryService inv = new InventoryService();

    public void addToCart(Customer c, int id, int qty) {

        Product p = inv.getProduct(id);

        if (p == null || p.getQuantity() < qty) {
            System.out.println("Invalid product!");
            return;
        }

        c.getCart().add(new CartItem(p, qty));
        p.setQuantity(p.getQuantity() - qty);

        System.out.println("Added to cart!");
    }

    public void viewCart(Customer c) {

        double total = 0;

        for (CartItem item : c.getCart()) {
            double cost = item.getProduct().getPrice() * item.getQuantity();
            total += cost;

            System.out.println(item.getProduct().getName() + " x " + item.getQuantity() + " = " + cost);
        }

        System.out.println("Total: " + total);
    }
}