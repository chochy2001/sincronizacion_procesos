
package productorconsumidor;
import java.util.Arrays;

class Producer extends Thread {
    Buffer buff;
    int number;

    public Producer(Buffer buff, int number) {
        this.buff = buff;
        this.number = number;
    }

    @Override
    public void run() {
        int totalProduced = 0;
        while (totalProduced < 50) {
            int quantity = 1 + (int)(Math.random() * 5); // Produciendo entre 1 y 5 unidades
            int[] values = new int[quantity];
            for (int i = 0; i < quantity; i++) {
                values[i] = totalProduced + i;
            }
            buff.put(values);
            System.out.println("Productor #" + this.number + " put: " + Arrays.toString(values));
            totalProduced += quantity;
            try {
                sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) { }
        }
    }
}

