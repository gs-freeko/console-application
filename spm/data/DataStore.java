package spm.data;

import spm.model.*;
import java.util.ArrayList;
import java.util.List;

public class DataStore {
    private static List<Admin> admins = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static List<Product> products = new ArrayList<>();

    public static List<Admin> getAdmins(){ return admins; }
    public static List<Customer> getCustomers(){ return customers; }
    public static List<Product> getProducts(){ return products; }

    static {
        admins.add(new Admin("admin@market.com", "admin123"));
        products.add(new Product(1, "Apple", 50, 100));
        products.add(new Product(2, "Milk", 30, 50));
    }
}