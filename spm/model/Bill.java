package spm.model;

import java.util.*;

public class Bill {

    private List<CartItem> items;
    private double total;

    public Bill(List<CartItem> items, double total) {
        this.items = items;
        this.total = total;
    }

    public List<CartItem> getItems() { return items; }
    public double getTotal() { return total; }
}