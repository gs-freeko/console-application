package spm.service;

import spm.model.Product;
import spm.model.Customer;
import spm.model.Admin;
import spm.data.DataStore;

import java.util.List;

public class ReportService {

    // Products with low quantity (threshold can be passed)
    public void lowStockProducts(int threshold) {
        System.out.println("Products with quantity less than " + threshold + ":");
        for (Product p : DataStore.getProducts()) {
            if (p.getQuantity() < threshold) {
                System.out.println(p.getName() + " - Quantity: " + p.getQuantity());
            }
        }
    }

    // Products never purchased
    public void neverPurchasedProducts() {
        System.out.println("Products never purchased:");
        List<Product> products = DataStore.getProducts();
        List<Customer> customers = DataStore.getCustomers();

        for (Product p : products) {
            boolean purchased = false;
            for (Customer c : customers) {
                for (var bill : c.getPurchaseHistory()) {
                    for (var item : bill.getItems()) {
                        if (item.getProduct().getId() == p.getId()) {
                            purchased = true;
                            break;
                        }
                    }
                    if (purchased) break;
                }
                if (purchased) break;
            }
            if (!purchased) {
                System.out.println(p.getName());
            }
        }
    }

    // Top spending customers
    public void topCustomers() {
        System.out.println("Customers by total purchase value:");
        for (Customer c : DataStore.getCustomers()) {
            double total = c.getPurchaseHistory().stream()
                    .mapToDouble(b -> b.getTotalAmount())
                    .sum();
            System.out.println(c.getEmail() + " - Total Purchased: " + total);
        }
    }

    // Admins with most sales
    public void topAdmins() {
        System.out.println("Admins by sales: (Requires sales tracking in future)");
        // Currently, we have no per-admin sales; you can extend DataStore to track which admin added the sale
        System.out.println("Feature can be extended to track admin sales.");
    }
}
