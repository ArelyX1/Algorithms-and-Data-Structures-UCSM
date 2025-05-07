package general;
import java.util.Scanner;

public class Test32 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int maxPrioridades;

        System.out.println("Ingrese la cantidad de prioridades (ejemplo: 6): ");
        maxPrioridades = sc.nextInt();
        sc.nextLine();

        PriorityQueueLinked<String> cola = new PriorityQueueLinked<>(maxPrioridades);

        int opcion;
        do {
            System.out.println("\n##### COLA DE PRIORIDAD #####");
            System.out.println("1. Encolar elemento");
            System.out.println("2. Desencolar elemento (mayor prioridad)");
            System.out.println("3. Ver elemento al frente (mayor prioridad)");
            System.out.println("4. Ver elemento al final (menor prioridad)");
            System.out.println("5. ¿La cola está vacía?");
            System.out.println("6. Mostrar estado de la cola");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el elemento: ");
                    String elem = sc.nextLine();
                    System.out.print("Ingrese la prioridad (0 = más alta, " + (maxPrioridades-1) + " = más baja): ");
                    int prio = sc.nextInt();
                    sc.nextLine();
                    try {
                        cola.enqueue(elem, prio);
                        System.out.println("Elemento encolado con prioridad " + prio + ".");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        String eliminado = cola.dequeue();
                        System.out.println("Elemento desencolado: " + eliminado);
                    } catch (ExceptionIsEmpty e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Elemento al frente: " + cola.front());
                    } catch (ExceptionIsEmpty e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Elemento al final: " + cola.back());
                    } catch (ExceptionIsEmpty e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println(cola.isEmpty() ? "La cola está vacía." : "La cola no está vacía.");
                    break;
                case 6:
                    System.out.println("Estado actual de la cola:");
                    System.out.println(cola.toString());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        sc.close();
    }
}
