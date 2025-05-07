package general;
public class QueueLink <E> implements Queue<E>{
    private Nodo<E> first;
    private Nodo<E> last;

    public QueueLink(){
        first = last = null;
    }
    public boolean isEmpty(){
        return first == null;
    }

    public void enqueue(E x){
        Nodo<E> nuevo = new Nodo<E>(x);
        if (isEmpty())
            first = nuevo;
        else{
            last.setNext(nuevo);
        }
        last = nuevo;
    }
    public E dequeue() throws ExceptionIsEmpty{
        if(isEmpty())
            throw new ExceptionIsEmpty("Cola vacia");
        E ele=first.getData();
        first=first.getNext();
        return ele;
    }
    public E back() throws ExceptionIsEmpty{
        if(isEmpty())
            throw new ExceptionIsEmpty("Cola vacia");
        return last.getData();
    }
    public E front() throws ExceptionIsEmpty{
        if(isEmpty())
            throw new ExceptionIsEmpty("Cola vacia");
        return first.getData();
    }
    @Override
    public String toString() {
        String resultado = "[";
        Nodo<E> actual = first;
        while (actual != null) {
            resultado += actual.getData();
            if (actual.getNext() != null) {
                resultado += ", ";
            }
            actual = actual.getNext();
        }
        resultado += "]";
        return resultado;
    }
}

