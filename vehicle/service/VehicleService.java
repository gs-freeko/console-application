package vehicle.service;

import vehicle.model.*;
import vehicle.util.*;
import java.util.*;

public class VehicleService {

    public void addVehicle(Scanner sc) {
        ConsoleUtil.header("ADD VEHICLE");

        System.out.print("Type (CAR/BIKE): ");
        String type = sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Quantity: ");
        int qty = sc.nextInt();
        System.out.print("Price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        int id = DataStore.vehicles.size() + 1;
        DataStore.vehicles.add(new Vehicle(id, name, type, qty, price));

        ConsoleUtil.success("Vehicle added successfully!");
    }

    public void viewVehicles() {
        ConsoleUtil.header("VEHICLE LIST");
        ConsoleUtil.line();

        System.out.printf("%-5s %-10s %-8s %-10s %-10s\n", "ID","NAME","TYPE","PRICE","STATUS");

        for (Vehicle v : DataStore.vehicles) {
            String status = v.isRented() ? "Rented" : "Available";

            System.out.printf("%-5d %-10s %-8s %-10.0f %-10s\n",
                    v.getId(), v.getName(), v.getType(), v.getPrice(), status);
        }

        ConsoleUtil.line();
    }

    public Vehicle getVehicleById(int id) {
        for (Vehicle v : DataStore.vehicles) {
            if (v.getId() == id) return v;
        }
        return null;
    }
}