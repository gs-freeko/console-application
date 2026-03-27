package spm.service;

import spm.data.DataStore;
import spm.model.Product;

public class InventoryService {

    public void viewProducts() {

        System.out.println("\n+----+----------------------+----------+----------+");
        System.out.printf("| %-2s | %-20s | %-8s | %-8s |\n", "ID", "Name", "Price", "Qty");
        System.out.println("+----+----------------------+----------+----------+");

        for (Product p : DataStore.products) {
            System.out.printf("| %-2d | %-20s | %-8.2f | %-8d |\n",
                    p.getId(), p.getName(), p.getPrice(), p.getQuantity());
        }

        System.out.println("+----+----------------------+----------+----------+");
    }

    public Product getProduct(int id) {
        for (Product p : DataStore.products) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public void addProduct(int id, String name, double price, int qty) {
        DataStore.products.add(new Product(id, name, price, qty));
        System.out.println("Product added!");
    }

    public void deleteProduct(int id) {
        DataStore.products.removeIf(p -> p.getId() == id);
        System.out.println("Product deleted!");
    }
}