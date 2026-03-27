package spm.service;

import spm.model.*;

public class PaymentService {

    public void checkout(Customer c) {

        double total = 0;

        for (CartItem item : c.getCart()) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }

        if (total > c.getCredit()) {
            System.out.println("Insufficient credit!");
            return;
        }

        c.setCredit(c.getCredit() - total);
        c.getCart().clear();

        System.out.println("Payment successful!");
        System.out.println("Remaining Credit: " + c.getCredit());
    }
}