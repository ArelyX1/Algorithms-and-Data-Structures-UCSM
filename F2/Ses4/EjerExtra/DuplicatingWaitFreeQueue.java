package EjerExtra;

public class DuplicatingWaitFreeQueue<T> {  
    private final WaitFreeQueue<T> mainQueue;  
    private final WaitFreeQueue<T> replicaQueue;  

    public DuplicatingWaitFreeQueue(int maxThreads) {  
        this.mainQueue = new WaitFreeQueue<>(maxThreads);  
        this.replicaQueue = new WaitFreeQueue<>(maxThreads);  
    }  

    public void enqueue(T value, int threadId) {
        int retries = 0;
        final int MAX_RETRIES = 20;
        
        while (retries++ < MAX_RETRIES) {
            boolean mainSuccess = mainQueue.tryEnqueue(value, threadId);
            boolean replicaSuccess = replicaQueue.tryEnqueue(value, threadId);
            
            if (mainSuccess && replicaSuccess) return;
            
            // ToDo Mejorar el Rollback mejorado con búsqueda específica
            if (mainSuccess) performTargetedRollback(mainQueue, value);
            if (replicaSuccess) performTargetedRollback(replicaQueue, value);
        }
        
        throw new IllegalStateException("Error crítico en hilo " + threadId + " para valor: " + value);
    }
    
    private void performTargetedRollback(WaitFreeQueue<T> queue, T targetValue) {
        final int MAX_ROLLBACK_ATTEMPTS = 10;
        int attempts = 0;
        T current;
        
        do {
            current = queue.dequeue();
            attempts++;
        } while (current != null && !current.equals(targetValue) && attempts < MAX_ROLLBACK_ATTEMPTS);
        
        if (current != null && current.equals(targetValue)) {
            System.out.println(WaitFreeQueue.CYAN + "[ROLLBACK] Eliminado: " + current + WaitFreeQueue.RESET);
        }
    }
    
    
    

    public T dequeueMain() {  
        return mainQueue.dequeue();  
    }  

    public T dequeueReplica() {  
        return replicaQueue.dequeue();  
    }  
}
