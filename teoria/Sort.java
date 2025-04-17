import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        Integer[] numeros = {5, 3, 4, 15, 8, -2, 9};
        System.out.println("Desordenado:");
        System.out.println(Arrays.toString(numeros));
        
        RecursiveBubbleSort.bubbleSort(numeros, numeros.length);
        System.out.println("Ordenado:");
        System.out.println(Arrays.toString(numeros));
    }
}

class RecursiveBubbleSort {
    public static <T extends Number & Comparable<T>> void bubbleSort(T[] arr, int n) {
        if (n == 1) return;
        
        boolean swapped = false;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                // Intercambiar elementos
                T temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
                swapped = true;
            }
        }
        
        if (swapped) {
            bubbleSort(arr, n - 1);
        }
    }
}
