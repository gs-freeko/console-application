package vehicle.service;

import vehicle.model.*;
import vehicle.util.*;

public class ReportService {

    public void showReports() {
        ConsoleUtil.header("REPORTS");

        for (Vehicle v : DataStore.vehicles) {
            System.out.println(v.getName() + " ₹" + v.getPrice());
        }
    }
}