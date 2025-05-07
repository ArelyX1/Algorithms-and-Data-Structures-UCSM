package EjerExtra;

import java.util.concurrent.atomic.AtomicReference;

public class WFNode<T> {  
    public T value;  
    public AtomicReference<WFNode<T>> next;  

    public WFNode(T value) {  
        this.value = value;  
        this.next = new AtomicReference<>(null);  
    }  
}
