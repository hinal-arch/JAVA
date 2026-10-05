import java.util.concurrent.atomic.AtomicLong;

public class ParallelArraySum {

    static final int SIZE = 1_000_000;
    static final int THREADS = 4;

    static long wrongTotal = 0;
    static long synchronizedTotal = 0;
    static AtomicLong atomicTotal = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {

        int[] numbers = new int[SIZE];

        for (int i = 0; i < SIZE; i++) {
            numbers[i] = 1;
        }

        int chunk = SIZE / THREADS;

 
        Thread[] t1 = new Thread[THREADS];

        long start1 = System.nanoTime();

        for (int i = 0; i < THREADS; i++) {

            int start = i * chunk;
            int end = (i == THREADS - 1)
                    ? SIZE
                    : start + chunk;

            t1[i] = new Thread(() -> {

                for (int j = start; j < end; j++) {

                     wrongTotal += numbers[j];
                }
            });

            t1[i].start();
        }

        for (Thread t : t1) {
            t.join();
        }

        long time1 = System.nanoTime() - start1;

        System.out.println("Without synchronization:");
        System.out.println("Total = " + wrongTotal);
        System.out.println("Time = " + time1 + " ns");

         Thread[] t2 = new Thread[THREADS];

        long start2 = System.nanoTime();

        for (int i = 0; i < THREADS; i++) {

            int start = i * chunk;
            int end = (i == THREADS - 1)
                    ? SIZE
                    : start + chunk;

            t2[i] = new Thread(() -> {

                for (int j = start; j < end; j++) {

                    synchronized (ParallelArraySum.class) {
                        synchronizedTotal += numbers[j];
                    }
                }
            });

            t2[i].start();
        }

        for (Thread t : t2) {
            t.join();
        }

        long time2 = System.nanoTime() - start2;

        System.out.println("\nWith synchronized:");
        System.out.println("Total = " + synchronizedTotal);
        System.out.println("Time = " + time2 + " ns");

         Thread[] t3 = new Thread[THREADS];

        long start3 = System.nanoTime();

        for (int i = 0; i < THREADS; i++) {

            int start = i * chunk;
            int end = (i == THREADS - 1)
                    ? SIZE
                    : start + chunk;

            t3[i] = new Thread(() -> {

                for (int j = start; j < end; j++) {
                    atomicTotal.addAndGet(numbers[j]);
                }
            });

            t3[i].start();
        }

        for (Thread t : t3) {
            t.join();
        }

        long time3 = System.nanoTime() - start3;

        System.out.println("\nWith AtomicLong:");
        System.out.println("Total = " + atomicTotal.get());
        System.out.println("Time = " + time3 + " ns");
    }
}