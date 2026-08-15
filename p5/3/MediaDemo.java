abstract class Media {
    String title;
    int lateDays;

    Media(String title, int lateDays) {
        this.title = title;
        this.lateDays = lateDays;
    }

    abstract double lateFee();
}

class Book extends Media {

    Book(String title, int lateDays) {
        super(title, lateDays);
    }

    @Override
    double lateFee() {
        return lateDays * 2;
    }
}

class DVD extends Media {

    DVD(String title, int lateDays) {
        super(title, lateDays);
    }

    @Override
    double lateFee() {
        return lateDays * 5;
    }
}

class Magazine extends Media {

    Magazine(String title, int lateDays) {
        super(title, lateDays);
    }

    @Override
    double lateFee() {
        return lateDays * 1;
    }
}

public class MediaDemo {
    public static void main(String[] args) {

        Media[] items = {
            new Book("Java Basics", 3),
            new DVD("Avengers", 2),
            new Magazine("Tech Today", 5)
        };

        double total = 0;

        for (Media m : items) {

            double fee = m.lateFee();

            System.out.println(
                m.title + " Late Fee = " + fee
            );

            total += fee;
        }

        System.out.println("\nTotal late fees = " + total + "\n");
    }
}