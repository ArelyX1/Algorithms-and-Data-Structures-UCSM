public class Node <T>{
    T dato;
    Node<T> rightNode;
    Node<T> leftNode;
    Node(T dato) {
        this.dato = dato;
        this.rightNode = null;
        this.leftNode = null;
    }
}