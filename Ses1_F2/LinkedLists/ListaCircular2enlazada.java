package Ses1_F2.LinkedLists;
import java.io.Serializable;

class Node implements Serializable {
    int data;
    Node prev;
    Node next;

    public Node(int data) {
        this.data = data;
        this.prev = this;
        this.next = this;
    }
}


public class ListaCircular2enlazada implements Serializable {
    Node head;
    Node tail;
    int size;
    
    public ListaCircular2enlazada() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(int data) {
        Node newNode = new Node(data);
        
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            Node current = head;
            boolean inserted = false;
            
            do {
                if (newNode.data < current.data) {
                    newNode.prev = current.prev;
                    newNode.next = current;
                    current.prev.next = newNode;
                    current.prev = newNode;
                    
                    if (current == head) {
                        head = newNode;
                    }
                    inserted = true;
                    break;
                }
                current = current.next;
            } while (current != head);
            
            if (!inserted) {
                newNode.prev = tail;
                newNode.next = head;
                tail.next = newNode;
                head.prev = newNode;
                tail = newNode;
            }
        }
        size++;
    }

    public void insertFirst(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            newNode.prev = tail;
            head.prev = newNode;
            tail.next = newNode;
            head = newNode;
        }
        size++;
    }

    public void insertLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            newNode.next = head;
            tail.next = newNode;
            head.prev = newNode;
            tail = newNode;
        }
        size++;
    }

    public void destroyList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void remove(int data) {
        if (head == null) return;
        
        Node current = head;
        do {
            if (current.data == data) {
                if (size == 1) {
                    head = null;
                    tail = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    
                    if (current == head) head = current.next;
                    if (current == tail) tail = current.prev;
                }
                size--;
                return;
            }
            current = current.next;
        } while (current != head);
    }

    public void display() {
        if (head == null) {
            System.out.println("Lista vacía");
            return;
        }
        Node current = head;
        do {
            System.out.print(current.data + " ");
            current = current.next;
        } while (current != head);
        System.out.println();
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    
    public void sort() {
        if (head == null || size <= 1) {
            return;
        }
    
        Node sortedTail = head;
        Node current = sortedTail.next;
        
        for (int i = 1; i < size; i++) {
            Node nextNode = current.next;
            //*Desconecta el current node
            current.prev.next = current.next;
            current.next.prev = current.prev;
    
            //* Buscar 
            Node insertPos = head;
            while (insertPos != sortedTail.next && insertPos.data < current.data) {
                insertPos = insertPos.next;
            }
            if (insertPos == head) {
                current.prev = head.prev;
                current.next = head;
                head.prev.next = current;
                head.prev = current;
                head = current;
            } else {
                current.prev = insertPos.prev;
                current.next = insertPos;
                insertPos.prev.next = current;
                insertPos.prev = current;
            }
            if (insertPos == sortedTail.next) {
                sortedTail = current;
            }
            
            current = nextNode;
        }
        
        tail = sortedTail;
        tail.next = head;    //! FIX: Mantener enlace circular
        head.prev = tail;    //! FIX: Mantener enlace circular
    }
    public void mergeSort() {
        if (head == null || size <= 1) return;
        
        head = mergeSortRec(head);
        // Actualizar tail después de ordenar
        Node temp = head;
        while (temp.next != head) {
            temp = temp.next;
        }
        tail = temp;
        tail.next = head;
        head.prev = tail;
    }
    
    private Node mergeSortRec(Node start) {
        if (start == null || start.next == head) return start;
    
        Node middle = split(start);
        Node left = mergeSortRec(start);
        Node right = mergeSortRec(middle);
    
        return merge(left, right);
    }
    
    private Node split(Node start) {
        Node slow = start;
        Node fast = start.next;
        
        while (fast != head && fast.next != head) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        Node mid = slow.next;
        mid.prev = start.prev;
        start.prev.next = mid;
        slow.next = start;
        start.prev = slow;
        
        return mid;
    }
    
    private Node merge(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;
    
        Node result;
        if (left.data <= right.data) {
            result = left;
            result.next = merge(left.next, right);
        } else {
            result = right;
            result.next = merge(left, right.next);
        }
        
        result.next.prev = result;
        return result;
    }
    
}
