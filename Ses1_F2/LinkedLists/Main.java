package Ses1_F2.LinkedLists;
import java.io.*;

public class Main {
    private static final String DATA_FILE = "lista_state.dat";
    private static ListaCircular2enlazada lista = loadList();
    
    public static void main(String[] args) {
        try {
            if(args.length > 0) {
                switch(args[0]) {
                    case "insertFirst":
                        handleInsertFirst(args);
                        break;
                    case "insertLast":
                        handleInsertLast(args);
                        break;
                    case "add":
                        handleAdd(args);
                        break;
                    case "remove":
                        handleRemove(args);
                        break;
                    case "display":
                        handleDisplay();
                        break;
                    case "destroyList":
                        handleDestroyList();
                        break;
                    case "insertionSort":
                        handleInsertionSort();
                        break;
                    case "mergeSort":
                        handleMergeSort();
                        break;
                }
                saveList();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void handleInsertFirst(String[] args) {
        if(args.length > 1) {
            lista.insertFirst(Integer.parseInt(args[1]));
            System.out.println("Insertado al inicio: " + args[1]);
        }
    }

    private static void handleInsertLast(String[] args) {
        if(args.length > 1) {
            lista.insertLast(Integer.parseInt(args[1]));
            System.out.println("Insertado al final: " + args[1]);
        }
    }

    private static void handleAdd(String[] args) {
        if(args.length > 1) {
            lista.add(Integer.parseInt(args[1]));
            System.out.println("Insertado ordenado: " + args[1]);
        }
    }

    private static void handleRemove(String[] args) {
        if(args.length > 1) {
            int valor = Integer.parseInt(args[1]);
            lista.remove(valor);
            System.out.println("Eliminado: " + valor);
        }
    }

    private static void handleDisplay() {
        if(lista.isEmpty()) {
            System.out.println("Lista vacía");
        } else {
            System.out.print("Elementos: ");
            lista.display();
        }
    }

    private static void handleDestroyList() {
        lista.destroyList();
        System.out.println("Lista destruida");
    }

    private static ListaCircular2enlazada loadList() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            return (ListaCircular2enlazada) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ListaCircular2enlazada();
        } catch (Exception e) {
            System.err.println("Error cargando lista: " + e.getMessage());
            return new ListaCircular2enlazada();
        }
    }

    private static void handleInsertionSort(){
        if(lista.isEmpty()){
            System.out.println("Lista vacía");
        } else {
            lista.sort();
            System.out.println("Lista ordenada");
        }
    }

    private static void handleMergeSort(){
        if(lista.isEmpty()){
            System.out.println("Lista vacía");
        } else {
            lista.mergeSort();
            System.out.println("Lista ordenada");
        }
    }

    private static void saveList() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(lista);
        } catch (Exception e) {
            System.err.println("Error guardando lista: " + e.getMessage());
        }
    }
}
