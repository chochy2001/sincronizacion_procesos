
package productorconsumidor;

import java.util.Arrays;


class Consumer extends Thread {
    Buffer buff;
    int number;

    public Consumer(Buffer c, int number) {
        buff = c;
        this.number = number;
    }

    @Override
    public void run() {
        int totalConsumed = 0;
        while (totalConsumed < 50) {
            int quantity = 1 + (int)(Math.random() * 5); // Consumiendo entre 1 y 5 unidades
            int[] values = buff.get(quantity);
            System.out.println("Consumidor" + this.number + " got: " + Arrays.toString(values));
            totalConsumed += values.length;
        }
    }
}

