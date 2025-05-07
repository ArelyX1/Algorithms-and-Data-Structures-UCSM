package general;

public class PriorityQueueLinkSort <E, N extends Comparable <N>>implements PriorityQueue<E, N> {
    class EntryNode {
        E data;
        N priority;

        EntryNode(E data, N priority) {
            this.data = data;
            this.priority = priority;
        }
    }

    private Nodo<EntryNode> first;
    private Nodo<EntryNode> last;

    public PriorityQueueLinkSort() {
        this.first = null;
        this.last = null;
    }

    public void enqueue(E x, N pr) {
        EntryNode entry = new EntryNode(x, pr);
        Nodo<EntryNode> nuevo = new Nodo<>(entry);
        if (isEmpty()) {
            first = last = nuevo;
        }else{
            Nodo<EntryNode> actual = first;
            Nodo<EntryNode> anterior = null;

            while(actual!=null && pr.compareTo(actual.getData().priority) >= 0){
                anterior = actual;
                actual = actual.getNext();
            }
            if(anterior == null){
                nuevo.setNext(first);
                first = nuevo;
            }else{
                anterior.setNext(nuevo);
                nuevo.setNext(actual);
                if (actual == null) {
                    last = nuevo;
                }
            }
        }
    }

    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Esta vacio...");
        E actual = this.first.getData().data;
        this.first = this.first.getNext();
        if (this.first == null)
            this.last = null;
        return actual;
    }
    public E back() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Esta vacio...");
        return last.getData().data;
    }
    public E front() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Esta vacio...");
        return first.getData().data;
    }
    public boolean isEmpty() {return first == null;}
    public String toString(){
        String resultado = "";
        Nodo<EntryNode> actual = first;
        while (actual != null) {
            resultado += "(" + actual.getData().data + ", " + actual.getData().priority + ") -> ";
            actual = actual.getNext();
        }
        resultado += "null";
        return resultado;
    }
}
