package vehicle;

import vehicle.service.*;
import vehicle.model.*;
import vehicle.util.*;
import java.util.*;

public class MainVehicle {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        AuthService auth = new AuthService();
        VehicleService vehicleService = new VehicleService();
        RentalService rentalService = new RentalService();
        ReportService reportService = new ReportService();

        while (true) {
            ConsoleUtil.header("VEHICLE RENTAL SYSTEM");
            System.out.println("1. Login\n2. Signup\n3. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                User user = auth.login(sc);

                if (user != null) {

                    if (user.getRole().equals("ADMIN")) {
                        while (true) {
                            ConsoleUtil.header("ADMIN DASHBOARD");
                            System.out.println("1.Add Vehicle\n2.View Vehicles\n3.Reports\n4.Logout");

                            int ch = sc.nextInt();
                            sc.nextLine();

                            if (ch == 1) vehicleService.addVehicle(sc);
                            else if (ch == 2) vehicleService.viewVehicles();
                            else if (ch == 3) reportService.showReports();
                            else break;
                        }
                    } else {
                        while (true) {
                            ConsoleUtil.header("USER DASHBOARD");
                            System.out.println("1.View Vehicles\n2.Rent\n3.Return\n4.History\n5.Logout");

                            int ch = sc.nextInt();
                            sc.nextLine();

                            if (ch == 1) vehicleService.viewVehicles();
                            else if (ch == 2) rentalService.rentVehicle(user, sc);
                            else if (ch == 3) rentalService.returnVehicle(user, sc);
                            else if (ch == 4) rentalService.history(user);
                            else break;
                        }
                    }
                }

            } else if (choice == 2) {
                auth.signup(sc);
            } else break;
        }
    }
}