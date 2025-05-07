package general;

public class Test2 {
    public static void main(String[] args) {
        try {
            // Crear una cola de enteros
            QueueLink<Integer> cola = new QueueLink<>();
            System.out.println("Cola vacía: " + cola.isEmpty());

            // Agregar elementos a la cola
            cola.enqueue(10);
            cola.enqueue(20);
            cola.enqueue(30);
            System.out.println("Cola después de encolar 10, 20 y 30: " + cola);

            // Ver el primer y último elemento
            System.out.println("Primer elemento: " + cola.front());
            System.out.println("Último elemento: " + cola.back());

            // Eliminar el primer elemento (dequeue)
            cola.dequeue();
            System.out.println("Cola después de hacer dequeue: " + cola);

            // Ver el primer y último elemento nuevamente
            System.out.println("Primer elemento después de dequeue: " + cola.front());
            System.out.println("Último elemento después de dequeue: " + cola.back());

            // Vaciar la cola
            cola.dequeue();
            System.out.println("Cola vacía: " + cola.isEmpty());

            System.out.println(cola);

        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}
