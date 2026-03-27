package spm.model;

import java.util.*;

public class Customer extends User {

    private double credit = 1000;
    private List<CartItem> cart = new ArrayList<>();

    public Customer(String email, String password) {
        super(email, password);
    }

    public double getCredit() { return credit; }
    public void setCredit(double credit) { this.credit = credit; }

    public List<CartItem> getCart() { return cart; }
}