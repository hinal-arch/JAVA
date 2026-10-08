public class DeadlockExample {
    static final Object lock1 = new Object();
    static final Object lock2 = new Object();

    static void createDeadlock() {
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1 locked Lock 1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
                synchronized (lock2) {
                    System.out.println("Thread 1 locked Lock 2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2 locked Lock 2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
                synchronized (lock1) {
                    System.out.println("Thread 2 locked Lock 1");
                }
            }
        });

        t1.start();
        t2.start();
    }

    static void removeDeadlock() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Fixed Thread 1 locked Lock 1");
                synchronized (lock2) {
                    System.out.println("Fixed Thread 1 locked Lock 2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Fixed Thread 2 locked Lock 1");
                synchronized (lock2) {
                    System.out.println("Fixed Thread 2 locked Lock 2");
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Deadlock removed successfully");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Deadlock demonstration:");
        createDeadlock();

        Thread.sleep(500);

        System.out.println("Deadlock prevention demonstration:");
        removeDeadlock();
    }
}