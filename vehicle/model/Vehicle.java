package vehicle.model;

public class Vehicle {
    private int id;
    private String name;
    private String type;
    private int quantity;
    private double price;
    private boolean rented;

    public Vehicle(int id, String name, String type, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int q) { this.quantity = q; }
    public double getPrice() { return price; }
    public boolean isRented() { return rented; }
    public void setRented(boolean rented) { this.rented = rented; }
}