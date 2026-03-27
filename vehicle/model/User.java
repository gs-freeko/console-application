package vehicle.model;

public class User {
    private String email;
    private String password;
    private String role;
    private double deposit = 30000;

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public double getDeposit() { return deposit; }
    public void setDeposit(double deposit) { this.deposit = deposit; }
}