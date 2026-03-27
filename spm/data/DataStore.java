package spm.data;

import java.util.*;
import spm.model.*;

public class DataStore {

    public static List<Product> products = new ArrayList<>();
    public static List<Customer> customers = new ArrayList<>();
    public static List<Admin> admins = new ArrayList<>();

    static {
        admins.add(new Admin("admin@shop.com", "admin123"));
        customers.add(new Customer("user@shop.com", "user123"));

        products.add(new Product(1, "Rice", 50, 20));
        products.add(new Product(2, "Milk", 30, 10));
        products.add(new Product(3, "Oil", 120, 15));
    }
}