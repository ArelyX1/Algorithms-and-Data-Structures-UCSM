package actividades;

public class Actividad2 {
    public static void main(String[] args) {
        System.out.println("El número mayor es: " + maxNum(10, 20));
    }
    public static int maxNum(int a, int b) {
        int result;
        if (a >= b) {
            result = a;
        } else {
            result = b;
        }
        return result;
    }
}
