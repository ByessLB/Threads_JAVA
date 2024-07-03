package learn.Exercice;

public class ExerciceThreads {
        public static void main(String[] args) {
            Object monitor = new Object();
            PrintFlag printFlag = new PrintFlag(true);

            Thread thread1 = new Thread(new Thread1(monitor, printFlag));
            Thread thread2 = new Thread(new Thread2(monitor, printFlag));

            thread1.start();
            thread2.start();

            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
