class ParkingLot {
    private int twoWheelers = 0;
    private int fourWheelers = 0;

    private final int twoCap;
    private final int fourCap;

    private static long revenue = 0;

    // Constructor
    ParkingLot(int twoCap, int fourCap) {
        this.twoCap = twoCap;
        this.fourCap = fourCap;
    }

    // Park vehicle
    public void park(String type) {
        if (type.equals("two")) {
            if (twoWheelers < twoCap) {
                twoWheelers++;
                revenue += 20;
                System.out.println("Two-wheeler parked.");
            } else {
                System.out.println("Two-wheeler section Full");
            }
        } else if (type.equals("four")) {
            if (fourWheelers < fourCap) {
                fourWheelers++;
                revenue += 40;
                System.out.println("Four-wheeler parked.");
            } else {
                System.out.println("Four-wheeler section Full");
            }
        }
    }

    // Leave vehicle
    public void leave(String type) {
        if (type.equals("two")) {
            if (twoWheelers > 0) {
                twoWheelers--;
                System.out.println("Two-wheeler left.");
            } else {
                System.out.println("No two-wheeler to leave.");
            }
        } else if (type.equals("four")) {
            if (fourWheelers > 0) {
                fourWheelers--;
                System.out.println("Four-wheeler left.");
            } else {
                System.out.println("No four-wheeler to leave.");
            }
        }
    }


    public void display() {
        System.out.println("\nFinal Occupancy:");
        System.out.println("Two-wheelers: " + twoWheelers);
        System.out.println("Four-wheelers: " + fourWheelers);
        System.out.println("Revenue: " + revenue);
    }

    // Main method
    public static void main(String[] args) {
        ParkingLot p = new ParkingLot(2, 2);

        p.park("two");
        p.park("two");
        p.park("two"); 

        p.park("four");
        p.park("four");
        p.park("four");      

        p.leave("two");
        p.leave("four");

        p.display();
    }
}
