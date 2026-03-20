package model;

public class Book {
    private String name;
    private String isbn;
    private int quantity;
    private double price;

    public Book(String name, String isbn, int quantity, double price) {
        this.name = name;
        this.isbn = isbn;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() { return name; }
    public String getIsbn() { return isbn; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}
