package vehicle.service;

import vehicle.model.*;
import vehicle.util.*;
import java.util.*;

public class RentalService {

    public void rentVehicle(User user, Scanner sc) {
        System.out.print("Enter Vehicle ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Vehicle v = new VehicleService().getVehicleById(id);

        if (v == null || v.isRented()) {
            ConsoleUtil.error("Vehicle not available");
            return;
        }

        if (v.getType().equals("CAR") && user.getDeposit() < 10000 ||
            v.getType().equals("BIKE") && user.getDeposit() < 3000) {
            ConsoleUtil.error("Insufficient deposit");
            return;
        }

        v.setRented(true);
        DataStore.rentals.add(new Rental(user, v));

        ConsoleUtil.success("Vehicle rented successfully!");
    }

    public void returnVehicle(User user, Scanner sc) {
        System.out.print("Enter Vehicle ID: ");
        int id = sc.nextInt();

        for (Rental r : DataStore.rentals) {
            if (r.getUser() == user && r.getVehicle().getId() == id) {

                System.out.print("KM Driven: ");
                int km = sc.nextInt();
                sc.nextLine();

                System.out.print("Damage (LOW/MEDIUM/HIGH): ");
                String dmg = sc.nextLine();

                double base = r.getVehicle().getPrice();
                double extra = km > 500 ? base * 0.15 : 0;
                double damage = dmg.equals("LOW") ? 200 :
                                dmg.equals("MEDIUM") ? 500 : 750;

                double total = base + extra + damage;

                ConsoleUtil.line();
                System.out.println("Total Fine: ₹" + total);

                user.setDeposit(user.getDeposit() - total);

                r.setAmount(total);
                r.setStatus("RETURNED");
                r.getVehicle().setRented(false);

                ConsoleUtil.success("Vehicle returned successfully!");
                return;
            }
        }

        ConsoleUtil.error("Rental not found");
    }

    public void history(User user) {
        ConsoleUtil.header("RENTAL HISTORY");

        for (Rental r : DataStore.rentals) {
            if (r.getUser() == user) {
                System.out.println(r.getVehicle().getName() + " - " + r.getStatus() + " - ₹" + r.getAmount());
            }
        }
    }
}