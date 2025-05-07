package general;

public class StackArray<E> implements Stack<E>{
    private E[] array;
    private int tope;

    public StackArray(int n){
        this.tope=-1;
        this.array=(E[]) new Object[n];
    }

    public void push(E x) throws ExceptionIsFull {
        if(isFull()){
            throw new ExceptionIsFull("Stack lleno...");
        }
        array[++tope] = x;
    }
    public E pop() throws ExceptionIsEmpty {
        if(isEmpty()){
            throw new ExceptionIsEmpty("Stack vacio...");
        }
        return array[tope--];
    }
    public E top() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Stack vacio...");
        }
        return array[tope];
    }
    public boolean isEmpty(){
        return tope==-1;
    }
    public boolean isFull(){return tope == array.length - 1;}

    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i <= tope; i++) {
            result += array[i];
            if (i < tope) {
                result += ", ";
            }
        }
        result += "]";
        return result;
    }
}
