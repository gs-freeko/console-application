package spm.model;

import java.time.LocalDateTime;
import java.util.List;

public class Bill {
    private static int billCounter = 1;
    private int billNumber;
    private LocalDateTime billDate;
    private List<CartItem> items;
    private double totalAmount;

    public Bill(List<CartItem> items, double totalAmount){
        this.billNumber = billCounter++;
        this.billDate = LocalDateTime.now();
        this.items = items;
        this.totalAmount = totalAmount;
    }

    public int getBillNumber() { return billNumber; }
    public LocalDateTime getBillDate() { return billDate; }
    public List<CartItem> getItems() { return items; }
    public double getTotalAmount() { return totalAmount; }
}
