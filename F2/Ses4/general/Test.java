package general;

public class Test{
    public static void main(String[] args) {
        try {
            Stack<Integer> pila = new StackArray<>(2);
            pila.push(5);
            pila.push(10);
            System.out.println("Tope: " + pila.top());
            System.out.println("Está llena: " + ((StackArray<Integer>) pila).isFull()); // true
            pila.pop();
            System.out.println("Tope después de pop: " + pila.top());
            System.out.println(pila);
        }catch (ExceptionIsEmpty e) {
            System.out.println("Excepción capturada: " + e.getMessage());
        }catch (ExceptionIsFull e){
            System.out.println("Excepción capturada: " + e.getMessage());
        }
    }
}