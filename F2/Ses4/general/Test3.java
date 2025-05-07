package general;

public class Test3 {
    public static void main(String[] args) {
        PriorityQueueLinkSort<String, Integer> cola = new PriorityQueueLinkSort<>();

        try {
            cola.enqueue("Tarea baja", 5);
            cola.enqueue("Tarea urgente", 1);
            cola.enqueue("Tarea media", 3);
            cola.enqueue("Tarea crítica", 0);

            System.out.println("Cola de prioridad:");
            System.out.println(cola.toString());

            System.out.println("Elemento al frente (mayor prioridad): " + cola.front());
            System.out.println("Elemento al final (menor prioridad): " + cola.back());

            System.out.println("Eliminando elemento con mayor prioridad: " + cola.dequeue());
            System.out.println("Cola actual:");
            System.out.println(cola.toString());

        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}