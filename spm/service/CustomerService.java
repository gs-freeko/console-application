package spm.service;

import spm.model.CartItem;
import spm.model.Customer;
import spm.model.Product;
import spm.model.Bill;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    private List<CartItem> cart = new ArrayList<>();

    public void addToCart(Product p, int qty){
        for(CartItem c : cart){
            if(c.getProduct().getId() == p.getId()){
                c.setQuantity(c.getQuantity() + qty);
                return;
            }
        }
        cart.add(new CartItem(p, qty));
    }

    public void removeFromCart(int productId){
        cart.removeIf(c -> c.getProduct().getId() == productId);
    }

    public void editCart(int productId, int newQty){
        for(CartItem c : cart){
            if(c.getProduct().getId() == productId){
                c.setQuantity(newQty);
                return;
            }
        }
    }

    public Bill checkout(Customer customer){
        double total = 0;
        for(CartItem c : cart){
            total += c.getProduct().getPrice() * c.getQuantity();
        }

        if(total > customer.getCredit()){
            System.out.println("Insufficient Credit!");
            return null;
        }

        customer.setCredit(customer.getCredit() - total);

        // Loyalty & reward
        if(total >= 5000){
            customer.setCredit(customer.getCredit() + 100);
        } else {
            int points = (int)(total / 100);
            customer.addLoyaltyPoints(points);
            if(customer.getLoyaltyPoints() >= 50){
                customer.setCredit(customer.getCredit() + 100);
                customer.addLoyaltyPoints(-50);
            }
        }

        Bill bill = new Bill(new ArrayList<>(cart), total);
        customer.addBill(bill);
        cart.clear();
        return bill;
    }
}
