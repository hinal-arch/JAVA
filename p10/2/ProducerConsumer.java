import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {
    static Queue<Integer> buffer = new LinkedList<>();
    static final int CAPACITY = 3;
    static final int ITEMS = 10;

    public static void main(String[] args) throws InterruptedException {
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= ITEMS; i++) {
                synchronized (buffer) {
                    while (buffer.size() == CAPACITY) {
                        try {
                            buffer.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    buffer.add(i);
                    System.out.println("Produced: " + i);
                    buffer.notify();
                }
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= ITEMS; i++) {
                synchronized (buffer) {
                    while (buffer.isEmpty()) {
                        try {
                            buffer.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    int value = buffer.remove();
                    System.out.println("Consumed: " + value);
                    buffer.notify();
                }
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("Production and consumption completed");
    }
}