package vehicle.model;

public class Rental {
    private User user;
    private Vehicle vehicle;
    private double amount;
    private String status;

    public Rental(User user, Vehicle vehicle) {
        this.user = user;
        this.vehicle = vehicle;
        this.status = "RENTED";
    }

    public User getUser() { return user; }
    public Vehicle getVehicle() { return vehicle; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}