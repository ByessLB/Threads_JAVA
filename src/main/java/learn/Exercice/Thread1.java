package learn.Exercice;

public class Thread1 implements Runnable {
    private final Object monitor;
    private PrintFlag printFlag;

    public Thread1(Object monitor, PrintFlag printFlag) {
        this.monitor = monitor;
        this.printFlag = printFlag;
    }

    public Object getMonitor() {
        return monitor;
    }

    public PrintFlag getPrintFlag() {
        return printFlag;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (monitor) {
                while (!printFlag.isShouldPrint()) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Jour");
                printFlag.setShouldPrint(false);;
                monitor.notify();
            }
        }
    }
}

