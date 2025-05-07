package general;

public class Nodo<E> {
    private E data;
    private Nodo<E> next;

    public Nodo(E data) {
        this.data = data;
        this.next = null;
    }

    public E getData() {
        return data;
    }
    public Nodo<E> getNext() {
        return next;
    }
    public void setNext(Nodo<E> next) {
        this.next = next;
    }
}
