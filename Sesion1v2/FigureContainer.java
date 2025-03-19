package Sesion1v2;

class Node {
    Figura data;
    Node next;

    public Node(Figura data) {
        this.data = data;
        this.next = null;
    }
}

public class FigureContainer {
    private Node head;

    public FigureContainer() {
        this.head = null;
    }

    public int size() {
        int count = 0;
        Node temp = this.head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public Figura get(int index) {
        Node temp = this.head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    public void add(Figura figura) {
        Node newNode = new Node(figura);
        if (this.head == null) {
            this.head = newNode;
        } else {
            Node temp = this.head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public void print() {
        Node temp = this.head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public void delete(int index) {
        if (index == 0) {
            this.head = this.head.next;
        } else {
            Node temp = this.head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            temp.next = temp.next.next;
        }
    }

    public void update(int index, Rectangulo data) {
        Node temp = this.head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        temp.data = data;
    }
}
