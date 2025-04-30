public class MainMejorado {

    public static void main(String[] args) {
        GestorDeTarea<Tarea> gestor1 = new GestorDeTarea<>();
        GestorDeTarea<Tarea> gestor2 = new GestorDeTarea<>();

        // Agregar tareas
        gestor1.agregarTarea(new Tarea("Tarea K", 2));
        gestor1.agregarTarea(new Tarea("Tarea D", 5));

        // Insertar al inicio
        gestor1.insertarFirst(new Tarea("Tarea A", 10));

        // Inserción intermedia
        gestor1.insercionIntermedia(new Tarea("Tarea M", 7));

        System.out.println("Lista gestor1 después de agregar:");
        gestor1.mostrar();

        // Longitud de la lista
        System.out.println("Número de elementos en gestor1: " + gestor1.length());

        // Eliminar nodo
        System.out.println("\nEliminando 'Tarea B': " + gestor1.removeNode(new Tarea("Tarea B", 0)));
        System.out.println("Lista gestor1 después de eliminar 'Tarea B':");
        gestor1.mostrar();

        // Verificar si la lista está vacía
        System.out.println("\n¿Lista gestor1 está vacía?: " + gestor1.inEmptyList());

        // Crear segunda lista y agregar tareas
        gestor2.agregarTarea(new Tarea("Tarea E", 4));
        gestor2.agregarTarea(new Tarea("Tarea F", 6));

        System.out.println("\nLista gestor2:");
        gestor2.mostrar();

        // Concatenar listas
        gestor1.concatenar(gestor2);
        System.out.println("\nLista gestor1 después de concatenar gestor2:");
        gestor1.mostrar();

        // Comparar listas
        System.out.println("\n¿Son iguales gestor1 y gestor2?: " + gestor1.compararListas(gestor2));

        // Ordenar lista
        gestor1.ordenar();
        System.out.println("\nLista gestor1 después de ordenar (burbuja interna):");
        gestor1.mostrar();

        // Agregar más elementos desordenados para probar ordenarInsertion
        gestor1.agregarTarea(new Tarea("Tarea Z", 3));
        gestor1.agregarTarea(new Tarea("Tarea B", 8));
        System.out.println("\nLista gestor1 antes de ordenarInsertion:");
        gestor1.mostrar();

        gestor1.ordenarInsertion();
        System.out.println("\nLista gestor1 después de ordenarInsertion:");
        gestor1.mostrar();

        // Destruir lista
        gestor1.destroyList();
        System.out.println("\nLista gestor1 después de destroyList:");
        gestor1.mostrar();

        // Confirmar que está vacía
        System.out.println("¿Lista gestor1 está vacía?: " + gestor1.inEmptyList());
    }
}

class Tarea implements Comparable<Tarea> {
    private String nombre;
    private int prioridad;

    public Tarea(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public int compareTo(Tarea otra) {
        return Integer.compare(this.prioridad, otra.prioridad);
    }

    @Override
    public String toString() {
        return nombre + " (prio=" + prioridad + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tarea tarea = (Tarea) obj;
        return nombre.equals(tarea.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.hashCode();
    }
}

class GestorDeTarea<T extends Comparable<T>> {
    private ListaEnlazada<T> lista;

    public GestorDeTarea() {
        lista = new ListaEnlazada<>();
    }

    public void agregarTarea(T tarea) {
        lista.agregarTarea(tarea);
    }

    public void insertarFirst(T tarea) {
        lista.insertarFirst(tarea);
    }

    public void insercionIntermedia(T tarea) {
        lista.insercionIntermedia(tarea);
    }

    public boolean removeNode(T tarea) {
        return lista.removeNode(tarea);
    }

    public int length() {
        return lista.length();
    }

    public void destroyList() {
        lista.destroyList();
    }

    public boolean inEmptyList() {
        return lista.inEmptyList();
    }

    public void concatenar(GestorDeTarea<T> otraLista) {
        this.lista.concatenar(otraLista.lista);
    }

    public boolean compararListas(GestorDeTarea<T> otraLista) {
        return this.lista.compararListas(otraLista.lista);
    }

    public void ordenar() {
        this.lista.ordenar();
    }

    public void ordenarInsertion() {
        this.lista.ordenarInsertion();
    }

    public void mostrar() {
        lista.mostrar();
    }
}

class ListaEnlazada<T extends Comparable<T>> {
    private class Nodo {
        T dato;
        Nodo siguiente;

        public Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo primero;
    private int numElem;

    public ListaEnlazada() {
        primero = null;
        numElem = 0;
    }

    public void agregarTarea(T tarea) {
        Nodo nuevo = new Nodo(tarea);
        if (primero == null) {
            primero = nuevo;
        } else {
            Nodo actual = primero;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        numElem++;
    }

    public void insertarFirst(T tarea) {
        Nodo nuevo = new Nodo(tarea);
        nuevo.siguiente = primero;
        primero = nuevo;
        numElem++;
    }

    public void insercionIntermedia(T tarea) {
        Nodo nuevo = new Nodo(tarea);
        if (primero == null || tarea.compareTo(primero.dato) < 0) {
            nuevo.siguiente = primero;
            primero = nuevo;
        } else {
            Nodo actual = primero;
            while (actual.siguiente != null && tarea.compareTo(actual.siguiente.dato) > 0) {
                actual = actual.siguiente;
            }
            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }
        numElem++;
    }

    public boolean removeNode(T tarea) {
        if (primero == null) {
            return false;
        }
        if (primero.dato.equals(tarea)) {
            primero = primero.siguiente;
            numElem--;
            return true;
        } else {
            Nodo actual = primero;
            while (actual.siguiente != null && !actual.siguiente.dato.equals(tarea)) {
                actual = actual.siguiente;
            }
            if (actual.siguiente == null) {
                return false;
            } else {
                actual.siguiente = actual.siguiente.siguiente;
                numElem--;
                return true;
            }
        }
    }

    public int length() {
        return numElem;
    }

    public void destroyList() {
        primero = null;
        numElem = 0;
    }

    public boolean inEmptyList() {
        return primero == null;
    }

    public boolean compararListas(ListaEnlazada<T> otraLista) {
        Nodo actual1 = this.primero;
        Nodo actual2 = otraLista.primero;

        while (actual1 != null && actual2 != null) {
            if (!actual1.dato.equals(actual2.dato)) {
                return false;
            }
            actual1 = actual1.siguiente;
            actual2 = actual2.siguiente;
        }

        return actual1 == null && actual2 == null;
    }

    public void concatenar(ListaEnlazada<T> otraLista) {
        if (otraLista.primero == null) {
            return;
        }

        if (this.primero == null) {
            this.primero = otraLista.primero;
        } else {
            Nodo actual = this.primero;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = otraLista.primero;
        }
        this.numElem += otraLista.numElem;
    }

    public void ordenar() {
        if (primero == null || primero.siguiente == null) {
            return;
        }

        Nodo actual = primero.siguiente;
        while (actual != null) {
            Nodo temp = primero;
            while (temp != actual) {
                if (actual.dato.compareTo(temp.dato) < 0) {
                    T prev = temp.dato;
                    temp.dato = actual.dato;
                    actual.dato = prev;
                }
                temp = temp.siguiente;
            }
            actual = actual.siguiente;
        }
    }

    public void ordenarInsertion() {
        if (primero == null || primero.siguiente == null) {
            return;
        }

        ListaEnlazada<T> listaOrdenada = new ListaEnlazada<>();
        Nodo actual = primero;

        while (actual != null) {
            listaOrdenada.insercionIntermedia(actual.dato);
            actual = actual.siguiente;
        }

        this.primero = listaOrdenada.primero;
        this.numElem = listaOrdenada.numElem;
    }

    public void mostrar() {
        Nodo actual = primero;
        while (actual != null) {
            System.out.println(actual.dato);
            actual = actual.siguiente;
        }
    }
}