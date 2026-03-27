package spm.service;

import spm.data.DataStore;
import spm.model.Product;

public class ReportService {

    public void lowStock() {
        for (Product p : DataStore.products) {
            if (p.getQuantity() < 5) {
                System.out.println(p.getName() + " is low on stock!");
            }
        }
    }
}