
package productorconsumidor;


class Buffer {
    
  private static final int MAX_SIZE = 50;
  private int[] contents = new int[MAX_SIZE];
  private int currentSize = 0; // Tamaño actual del buffer
  //private boolean available = false;

  public synchronized int[] get(int n) {
    while (currentSize < n) {
        try {
            wait();
        } catch (InterruptedException e) { }
    }
    int[] retrieved = new int[n];
    for (int i = 0; i < n; i++) {
        retrieved[i] = contents[--currentSize];
    }
    available = currentSize > 0;
    notifyAll();
    return retrieved;
}

   
   public synchronized void put(int[] values) {
    while (currentSize + values.length > MAX_SIZE) {
        try {
            wait();
        } catch (InterruptedException e) { }
    }
    for (int value : values) {
        contents[currentSize++] = value;
    }
    available = true;
    notifyAll();
}

}
