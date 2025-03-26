package Actividad;

public class Exist {
    public static <T> boolean exist(T[] array, T element) {
        for (T item : array) {
            if (item.equals(element)) {
                return true;
            }
        }
        return false;
    }
}