import java.util.Arrays;

public class SortFuerzBruta {
    public static void main(String[] args) {
        Integer[] numeros = {5, 3, 4, 15, 8, -2, 9};
        System.out.println("Desordenado:");
        System.out.println(Arrays.toString(numeros));
        
        bubbleSort(numeros, numeros.length);
        
        System.out.println("Ordenado:");
        System.out.println(Arrays.toString(numeros));
    }

    public static <T extends Comparable<T>> void bubbleSort(T[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
