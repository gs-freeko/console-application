package spm.model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private double credit;
    private int loyaltyPoints;
    private List<Bill> purchaseHistory;

    public Customer(String email, String password){
        super(email, password);
        this.credit = 1000;
        this.loyaltyPoints = 0;
        this.purchaseHistory = new ArrayList<>();
    }

    public double getCredit() { return credit; }
    public void setCredit(double credit) { this.credit = credit; }
    public int getLoyaltyPoints() { return loyaltyPoints; }
    public void addLoyaltyPoints(int points) { this.loyaltyPoints += points; }
    public List<Bill> getPurchaseHistory() { return purchaseHistory; }
    public void addBill(Bill bill) { this.purchaseHistory.add(bill); }
}