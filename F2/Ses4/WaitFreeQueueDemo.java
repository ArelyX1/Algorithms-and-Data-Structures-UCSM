

import EjerExtra.WaitFreeQueue;

public class WaitFreeQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        final int THREADS = 3;
        final int ELEMENTS_PER_THREAD = 2;
        WaitFreeQueue<Integer> queue = new WaitFreeQueue<>(THREADS);

        // Hilos productores
        Thread[] producers = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            final int threadId = i;
            producers[i] = new Thread(() -> {
                for (int j = 0; j < ELEMENTS_PER_THREAD; j++) {
                    int value = threadId * 100 + j;
                    queue.enqueue(value, threadId);
                    System.out.println(Thread.currentThread().getName() + " encoló: " + value);
                }
            }, "Productor-" + (i + 1));
        }

        // Hilos consumidores
        Thread[] consumers = new Thread[2];
        for (int i = 0; i < 2; i++) {
            consumers[i] = new Thread(() -> {
                for (int j = 0; j < (THREADS * ELEMENTS_PER_THREAD) / 2; j++) {
                    Integer value = queue.dequeue();
                    System.out.println(Thread.currentThread().getName() + " desencoló: " + value);
                }
            }, "Consumidor-" + (i + 1));
        }

        System.out.println("Iniciando demostración...");
        for (Thread t : producers) t.start();
        for (Thread t : consumers) t.start();

        for (Thread t : producers) t.join();
        for (Thread t : consumers) t.join();
        System.out.println("Demostración completada");
    }
}
