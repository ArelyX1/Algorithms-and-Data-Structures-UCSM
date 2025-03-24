package Ejercicio_clase;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bolsa<E> implements Iterable<E> {
    protected Node<E> head;
    protected int size;

    protected static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    public Bolsa() {
        this.head = null;
        this.size = 0;
    }

    public void add(E objeto) {
        Node<E> newNode = new Node<>(objeto);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    
    
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E data = current.data;
                current = current.next;
                return data;
            }
        };
    } 

    public int size() {
        return size;
    }
}