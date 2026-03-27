package vehicle.service;

import vehicle.model.*;
import vehicle.util.*;
import java.util.*;

public class AuthService {

    public User login(Scanner sc) {
        ConsoleUtil.header("LOGIN");
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        for (User u : DataStore.users) {
            if (u.getEmail().equals(email) && u.getPassword().equals(pass)) {
                ConsoleUtil.success("Login successful!");
                return u;
            }
        }
        ConsoleUtil.error("Invalid credentials");
        return null;
    }

    public void signup(Scanner sc) {
        ConsoleUtil.header("SIGNUP");
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();
        System.out.print("Role (ADMIN/USER): ");
        String role = sc.nextLine();

        DataStore.users.add(new User(email, pass, role));
        ConsoleUtil.success("Account created successfully!");
    }
}