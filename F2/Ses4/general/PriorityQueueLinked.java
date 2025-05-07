package general;

public class PriorityQueueLinked<E> implements PriorityQueue<E, Integer> {
    private QueueLink<E>[] queues;
    private int maxPriorities;

    @SuppressWarnings("unchecked")
    public PriorityQueueLinked(int maxPriorities) {
        this.maxPriorities = maxPriorities;
        this.queues = (QueueLink<E>[]) new QueueLink[maxPriorities];
        for (int i = 0; i < maxPriorities; i++) {
            queues[i] = new QueueLink<>();
        }
    }

    @Override
    public void enqueue(E x, Integer pr) {
        if (pr < 0 || pr >= maxPriorities) {
            throw new IllegalArgumentException("Prioridad inválida: " + pr);
        }
        queues[pr].enqueue(x);
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        for (int i = 0; i < maxPriorities; i++) {
            if (!queues[i].isEmpty()) {
                return queues[i].dequeue();
            }
        }
        throw new ExceptionIsEmpty("La cola de prioridad está vacía");
    }

    @Override
    public E front() throws ExceptionIsEmpty {
        for (int i = 0; i < maxPriorities; i++) {
            if (!queues[i].isEmpty()) {
                return queues[i].front();
            }
        }
        throw new ExceptionIsEmpty("La cola de prioridad está vacía");
    }

    @Override
    public E back() throws ExceptionIsEmpty {
        for (int i = maxPriorities - 1; i >= 0; i--) {
            if (!queues[i].isEmpty()) {
                return queues[i].back();
            }
        }
        throw new ExceptionIsEmpty("La cola de prioridad está vacía");
    }

    @Override
    public boolean isEmpty() {
        for (QueueLink<E> queue : queues) {
            if (!queue.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxPriorities; i++) {
            if (!queues[i].isEmpty()) {
                sb.append("Prioridad ").append(i).append(": ").append(queues[i].toString()).append("\n");
            }
        }
        return sb.toString();
    }
}
