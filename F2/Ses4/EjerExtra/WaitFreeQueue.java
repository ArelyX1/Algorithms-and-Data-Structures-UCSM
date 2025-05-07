package EjerExtra;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class WaitFreeQueue<T> {
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    public static final String RESET = "\u001B[0m";
    
    private final AtomicReference<WFNode<T>> head;
    private final AtomicReference<WFNode<T>> tail;
    private final AtomicReferenceArray<WFNode<T>> announcements;

    public WaitFreeQueue(int maxThreads) {
        WFNode<T> dummy = new WFNode<>(null);
        this.head = new AtomicReference<>(dummy);
        this.tail = new AtomicReference<>(dummy);
        this.announcements = new AtomicReferenceArray<>(maxThreads);
    }

    public boolean tryEnqueue(T value, int threadId) {
        WFNode<T> newNode = new WFNode<>(value);
        announcements.set(threadId, newNode);
        System.out.println(YELLOW + "[Hilo " + threadId + "] Anunciando encolado: " + value + RESET);
        
        final int MAX_ATTEMPTS = 30; // Aumentar límite de intentos
        int attempts = 0;
        
        while (attempts++ < MAX_ATTEMPTS) {
            // Priorizar auto-ayuda primero
            helpIfNeeded(threadId);
            
            WFNode<T> currentTail = tail.get();
            WFNode<T> tailNext = currentTail.next.get();
            
            if (currentTail == tail.get()) {
                if (tailNext != null) {
                    tail.compareAndSet(currentTail, tailNext);
                } else {
                    if (currentTail.next.compareAndSet(null, newNode)) {
                        tail.compareAndSet(currentTail, newNode);
                        announcements.compareAndSet(threadId, newNode, null);
                        System.out.println(GREEN + "[Hilo " + threadId + "] Encolado exitoso: " + value + RESET);
                        return true;
                    }
                }
            }
            
            // Ayudar a otros hilos después de 3 intentos propios
            if (attempts % 3 == 0) {
                helpEnqueue(threadId);
            }
            
            if (announcements.get(threadId) == null) return true;
        }
        
        announcements.compareAndSet(threadId, newNode, null);
        System.err.println("Error: Hilo " + threadId + " falló después de " + MAX_ATTEMPTS + " intentos");
        return false;
    }

    
    

    private void helpEnqueue(int requesterId) {
        helpIfNeeded(requesterId);
        for (int i = 0; i < announcements.length(); i++) {
            if (i != requesterId) helpIfNeeded(i);
        }
    }

    private void helpIfNeeded(int threadIndex) {
        WFNode<T> node = announcements.get(threadIndex);
        if (node == null) return;

        WFNode<T> currentTail = tail.get();
        WFNode<T> tailNext = currentTail.next.get();
        
        if (currentTail != tail.get()) return;
        
        if (tailNext != null) {
            tail.compareAndSet(currentTail, tailNext);
        } else {
            if (currentTail.next.compareAndSet(null, node)) {
                tail.compareAndSet(currentTail, node);
                announcements.compareAndSet(threadIndex, node, null);
                System.out.println(CYAN + "[HELP] Hilo ayudó a hilo " + threadIndex + " con valor: " + node.value + RESET);
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
