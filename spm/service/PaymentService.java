package spm.service;

import spm.model.Bill;
import spm.model.CartItem;
import spm.model.Customer;

import java.util.List;

public class PaymentService {

    public Bill processPayment(Customer customer, List<CartItem> cartItems) {
        double total = 0;
        for (CartItem c : cartItems) {
            total += c.getProduct().getPrice() * c.getQuantity();
        }

        if (total > customer.getCredit()) {
            System.out.println("Insufficient credit! Payment failed.");
            return null;
        }

        customer.setCredit(customer.getCredit() - total);

        // Rewards logic
        if (total >= 5000) {
            customer.setCredit(customer.getCredit() + 100); // Payback to credit
        } else {
            int points = (int) (total / 100); // 1 point per 100 Rs
            customer.addLoyaltyPoints(points);

            if (customer.getLoyaltyPoints() >= 50) {
                customer.setCredit(customer.getCredit() + 100); // Reward for 50 points
                customer.addLoyaltyPoints(-50);
            }
        }

        Bill bill = new Bill(cartItems, total);
        customer.addBill(bill);

        System.out.println("Payment successful! Total: " + total + ", Remaining Credit: " + customer.getCredit());
        return bill;
    }
}