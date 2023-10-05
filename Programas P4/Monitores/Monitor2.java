 class MonitorExample {
    private int count = 0;

    public synchronized void increment() {
        while (count == 2) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        count++;
        notifyAll();
    }

    public synchronized void decrement() {
        while (count == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        count--;
        notifyAll();
    }

    public synchronized int getCount() {
        return count;
    }
}

 class Incrementer implements Runnable {
    private MonitorExample monitor;

    public Incrementer(MonitorExample monitor) {
        this.monitor = monitor;
    }

    public void run() {
        try {
            while (true) {
                monitor.increment();
                monitor.increment();

                System.out.println("Contador incrementado: " + monitor.getCount());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

 class Decrementer implements Runnable {
    private MonitorExample monitor;

    public Decrementer(MonitorExample monitor) {
        this.monitor = monitor;
    }

    public void run() {
        try {
            while (true) {
                monitor.decrement();
                System.out.println("Contador decrementado: " + monitor.getCount());
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class Monitor2 {
    public static void main(String[] args) {
        MonitorExample monitor = new MonitorExample();
        Thread incrementerThread = new Thread(new Incrementer(monitor));
        Thread decrementerThread = new Thread(new Decrementer(monitor));
        incrementerThread.start();
        decrementerThread.start();
    }

}