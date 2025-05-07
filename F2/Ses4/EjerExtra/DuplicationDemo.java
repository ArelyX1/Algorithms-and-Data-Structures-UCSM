package EjerExtra;

public class DuplicationDemo {
    public static void main(String[] args) throws InterruptedException {
        final int PROD_THREADS = 3;
        final int TOTAL_ELEMENTS = 9;
        
        DuplicatingWaitFreeQueue<Integer> queue = new DuplicatingWaitFreeQueue<>(PROD_THREADS);

        Thread[] producers = new Thread[PROD_THREADS];
        for (int i = 0; i < PROD_THREADS; i++) {
            final int threadId = i;
            producers[i] = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    int value = threadId * 100 + j;
                    try {
                        queue.enqueue(value, threadId);
                        // Tiempos de sleep ajustados para mejor coordinación
                        Thread.sleep(threadId == 0 ? 40 : 20);
                    } catch (InterruptedException e) {
                        System.out.println(WaitFreeQueue.RED + "[INTERRUPCIÓN] Hilo " + threadId + " interrumpido" + WaitFreeQueue.RESET);
                        Thread.currentThread().interrupt();
                        return;
                    } catch (IllegalStateException e) {
                        System.out.println(WaitFreeQueue.RED + "[FALLO] " + e.getMessage() + WaitFreeQueue.RESET);
                        return;
                    }
                }
            }, "Productor-" + (i + 1));
        }

        // * Configuración de consumidores con verificación de finalización
        Thread mainConsumer = new Thread(() -> {
            int processed = 0;
            while (processed < TOTAL_ELEMENTS) {
                Integer value = queue.dequeueMain();
                if (value != null) {
                    System.out.println(WaitFreeQueue.RED + "[MAIN] Procesado: " + value + WaitFreeQueue.RESET);
                    processed++;
                }
                try { Thread.sleep(25); } catch (InterruptedException e) { break; }
            }
        });

        Thread replicaConsumer = new Thread(() -> {
            int processed = 0;
            while (processed < TOTAL_ELEMENTS) {
                Integer value = queue.dequeueReplica();
                if (value != null) {
                    System.out.println(WaitFreeQueue.BLUE + "[REPLICA] Procesado: " + value + WaitFreeQueue.RESET);
                    processed++;
                }
                try { Thread.sleep(25); } catch (InterruptedException e) { break; }
            }
        });

        System.out.println("=== INICIO DEMO ===");
        long startTime = System.currentTimeMillis();
        
        for (Thread t : producers) t.start();
        mainConsumer.start();
        replicaConsumer.start();

        for (Thread t : producers) {
            t.join(10_000);
            if (t.isAlive()) {
                System.err.println(WaitFreeQueue.RED + "[TIMEOUT] Productor no completado: " + t.getName() + WaitFreeQueue.RESET);
                t.interrupt();
            }
        }

        mainConsumer.join(5_000);
        replicaConsumer.join(5_000);
        
        System.out.println("\n=== ESTADÍSTICAS ===");
        System.out.println("Tiempo total: " + (System.currentTimeMillis() - startTime) + "ms");
        System.out.println("=== DEMO COMPLETADO ===");
        
        //!Forzar cierre si es necesario
        if (mainConsumer.isAlive() || replicaConsumer.isAlive()) {
            System.err.println(WaitFreeQueue.RED + "\n[ADVERTENCIA] Algunos consumidores no completaron su trabajo" + WaitFreeQueue.RESET);
            System.exit(1);
        }
    }
}
