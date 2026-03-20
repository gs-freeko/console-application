package spm.service;

import spm.model.Product;
import spm.data.DataStore;
import java.util.List;
import java.util.Comparator;

public class InventoryService {
    public Product searchProductById(int id){
        for(Product p : DataStore.getProducts()){
            if(p.getId() == id){
                return p;
            }
        }
        return null; // not found
    }

    public void addProduct(Product p){ DataStore.getProducts().add(p); }

    public void deleteProduct(int productId){
        DataStore.getProducts().removeIf(p -> p.getId() == productId);
    }

    public void modifyProductQuantity(int productId, int quantity){
        for(Product p : DataStore.getProducts()){
            if(p.getId() == productId){
                p.setQuantity(quantity);
                return;
            }
        }
    }

    public List<Product> listProductsByName(){
        List<Product> products = DataStore.getProducts();
        products.sort(Comparator.comparing(Product::getName));
        return products;
    }

    public List<Product> listProductsByPrice(){
        List<Product> products = DataStore.getProducts();
        products.sort(Comparator.comparing(Product::getPrice));
        return products;
    }

    public Product searchProduct(String name){
        for(Product p : DataStore.getProducts()){
            if(p.getName().equalsIgnoreCase(name)){
                return p;
            }
        }
        return null;
    }
}
