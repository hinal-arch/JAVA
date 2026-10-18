import java.util.Scanner;

public class TollBooth {
    record Vehicle(String number, String type) {}

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalToll = 0;
        int bike = 0, car = 0, truck = 0;

        while (true) {
            System.out.print("Enter vehicle number (or done): ");
            String number = sc.next();

            if (number.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter type (bike, car, truck): ");
            String type = sc.next().toLowerCase();
            Vehicle vehicle = new Vehicle(number, type);

            int toll = switch (vehicle.type()) {
                case "bike" -> 20;
                case "car" -> 50;
                case "truck" -> 150;
                default -> -1;
            };

            if (toll == -1) {
                System.out.println("Invalid vehicle type.");
                continue;
            }

            totalToll += toll;
            switch (vehicle.type()) {
                case "bike" -> bike++;
                case "car" -> car++;
                case "truck" -> truck++;
            }
        }

        System.out.println("Total toll: " + totalToll);

        if (bike == 0 && car == 0 && truck == 0) {
            System.out.println("No vehicles recorded.");
        } else if (car >= bike && car >= truck) {
            System.out.println("Most frequent: car");
        } else if (bike >= car && bike >= truck) {
            System.out.println("Most frequent: bike");
        } else {
            System.out.println("Most frequent: truck");
        }

        sc.close();
    }
}