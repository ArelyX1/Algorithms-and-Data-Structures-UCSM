

import java.util.concurrent.atomic.AtomicReference;

public class DynamicWaitFreeDemo {
    public static final String CYAN = "\u001B[36m";   // Color cian
    public static final String GREEN = "\u001B[32m";  // Color verde
    public static final String YELLOW = "\u001B[33m"; // Color amarillo (por si lo necesitas)
    public static final String RED = "\u001B[31m";    // Color rojo (por si lo necesitas)
    public static final String BLUE = "\u001B[34m";   // Color azul (por si lo necesitas)
    public static final String RESET = "\u001B[0m";   // Resetear color

    

    static class WFNode<T> {
        T value;
        AtomicReference<WFNode<T>> next = new AtomicReference<>(null);
        
        public WFNode(T value) {
            this.value = value;
        }
    }

    static class WaitFreeQueue<T> {
        private final AtomicReference<WFNode<T>> head;
        private final AtomicReference<WFNode<T>> tail;
        private final Object[] announcements;

        public WaitFreeQueue(int maxThreads) {
            WFNode<T> dummy = new WFNode<>(null);
            this.head = new AtomicReference<>(dummy);
            this.tail = new AtomicReference<>(dummy);
            this.announcements = new Object[maxThreads];
        }

        public void enqueue(T value, int threadId) {
            WFNode<T> newNode = new WFNode<>(value);
            announcements[threadId] = newNode;
            System.out.println(YELLOW + "[Hilo " + threadId + "] Anunciando encolado: " + value + RESET);

            while (announcements[threadId] != null) {
                helpEnqueue(threadId);
                WFNode<T> currentTail = tail.get();
                WFNode<T> tailNext = currentTail.next.get();

                if (currentTail == tail.get()) {
                    if (tailNext != null) {
                        tail.compareAndSet(currentTail, tailNext);
                    } else {
                        if (currentTail.next.compareAndSet(null, newNode)) {
                            tail.compareAndSet(currentTail, newNode);
                            announcements[threadId] = null;
                            System.out.println(GREEN + "[Hilo " + threadId + "] Encolado exitoso: " + value + RESET);
                        }
                    }
                }
            }
        }

        private void helpEnqueue(int helperId) {
            for (int i = 0; i < announcements.length; i++) {
                @SuppressWarnings("unchecked")
                WFNode<T> node = (WFNode<T>) announcements[i];
                if (node != null) {
                    WFNode<T> currentTail = tail.get();
                    WFNode<T> tailNext = currentTail.next.get();

                    if (currentTail != tail.get()) continue;

                    if (tailNext != null) {
                        tail.compareAndSet(currentTail, tailNext);
                    } else {
                        if (currentTail.next.compareAndSet(null, node)) {
                            tail.compareAndSet(currentTail, node);
                            announcements[i] = null;
                            System.out.println(CYAN + "[Hilo " + helperId + "] Ayudó a completar encolado: " + node.value + RESET);
                        }
                    }
                }
            }
        }

        public T dequeue() {
            while (true) {
                WFNode<T> currentHead = head.get();
                WFNode<T> currentTail = tail.get();
                WFNode<T> headNext = currentHead.next.get();

                if (currentHead == head.get()) {
                    if (currentHead == currentTail) {
                        if (headNext == null) return null;
                        tail.compareAndSet(currentTail, headNext);
                    } else {
                        T value = headNext.value;
                        if (head.compareAndSet(currentHead, headNext)) {
                            System.out.println(RED + "[Consumidor] Desencolado: " + value + RESET);
                            return value;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final int PROD_THREADS = 3;
        final int CONS_THREADS = 2;
        WaitFreeQueue<Integer> queue = new WaitFreeQueue<>(PROD_THREADS);

        System.out.println("=== DEMO COLA WAIT-FREE ===");
        System.out.println("Código de colores:");
        System.out.println(YELLOW + "Amarillo: Anuncio de operación" + RESET);
        System.out.println(CYAN + "Cian: Ayuda entre hilos" + RESET);
        System.out.println(GREEN + "Verde: Operación completada" + RESET);
        System.out.println(RED + "Rojo: Desencolado\n" + RESET);

        // Productores
        Thread[] producers = new Thread[PROD_THREADS];
        for (int i = 0; i < PROD_THREADS; i++) {
            final int threadId = i;
            producers[i] = new Thread(() -> {
                for (int j = 1; j <= 3; j++) {
                    int value = threadId * 100 + j;
                    queue.enqueue(value, threadId);
                    try { Thread.sleep((long)(Math.random() * 1000)); } catch (InterruptedException e) {}
                }
            }, "Productor-" + (i+1));
        }

        // Consumidores
        Thread[] consumers = new Thread[CONS_THREADS];
        for (int i = 0; i < CONS_THREADS; i++) {
            consumers[i] = new Thread(() -> {
                while (true) {
                    Integer value = queue.dequeue();
                    if (value != null) {
                        try { Thread.sleep(800); } catch (InterruptedException e) {}
                    }
                }
            }, "Consumidor-" + (i+1));
        }

        for (Thread t : producers) t.start();
        for (Thread t : consumers) t.start();

        // Timer para finalizar demo
        new Thread(() -> {
            try {
                Thread.sleep(10000);
                System.out.println("\n=== DEMO FINALIZADO ===");
                System.exit(0);
            } catch (InterruptedException e) {}
        }).start();
    }
}
