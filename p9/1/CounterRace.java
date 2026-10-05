class Counter {
    int count = 0;

     void increment() {
        count++;
    }

     synchronized void synchronizedIncrement() {
        count++;
    }
}

public class CounterRace {

    public static void main(String[] args) throws InterruptedException {

        int numberOfThreads = 10;
        int incrementsPerThread = 100000;

         Counter counter1 = new Counter();

        Thread[] threads1 = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads1[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    counter1.increment();
                }
            });
            threads1[i].start();
        }

        for (Thread t : threads1) {
            t.join();
        }

        System.out.println("Without synchronization:");
        System.out.println("Expected count: "
                + (numberOfThreads * incrementsPerThread));
        System.out.println("Actual count: " + counter1.count);


         Counter counter2 = new Counter();

        Thread[] threads2 = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads2[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    counter2.synchronizedIncrement();
                }
            });
            threads2[i].start();
        }

        for (Thread t : threads2) {
            t.join();
        }

        System.out.println("\nWith synchronization:");
        System.out.println("Expected count: "
                + (numberOfThreads * incrementsPerThread));
        System.out.println("Actual count: " + counter2.count);
    }
}