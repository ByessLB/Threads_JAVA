package learn.Exercice;

public class SimpleExerciceThreads {
    private static final Object MONITOR = new Object();
    private static boolean thread1Turn = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Thread1());
        Thread t2 = new Thread(new Thread2());

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class Thread1 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (MONITOR) {
                    while (!thread1Turn) {
                        try {
                            MONITOR.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Thread 1");
                    thread1Turn = false;
                    MONITOR.notify();
                }
            }
        }
    }

    public static class Thread2 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (MONITOR) {
                    while (thread1Turn) {
                        try {
                            MONITOR.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Thread 2");
                    thread1Turn = true;
                    MONITOR.notify();
                }
            }
        }
    }
}

