public class CinemaShow {
    private String title;
    private int seatsAvailable;
    private final int capacity;
    private static int totalBooked = 0;

    public CinemaShow(String title, int capacity) {
        this.title = title;
        this.capacity = capacity;
        seatsAvailable = capacity;
    }

    public CinemaShow(String title) {
        this(title, 100);
    }

    public boolean book(int n) {
        if (n <= seatsAvailable) {
            seatsAvailable -= n;
            totalBooked += n;
            return true;
        }
        return false;
    }

    public void cancel(int n) {
        seatsAvailable += n;
        if (seatsAvailable > capacity)
            seatsAvailable = capacity;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public static int getTotalBooked() {
        return totalBooked;
    }

    public static void main(String[] args) {
        CinemaShow s = new CinemaShow("Movie", 50);

        System.out.println(s.book(10) + " Seats: " + s.getSeatsAvailable());
        System.out.println(s.book(40) + " Seats: " + s.getSeatsAvailable());
        s.cancel(10);
        System.out.println("Seats: " + s.getSeatsAvailable());
        System.out.println("Total Booked: " + CinemaShow.getTotalBooked());
    }
}
