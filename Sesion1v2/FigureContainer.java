package Sesion1v2;

class Node {
    Figura data;
    double area;
    Node next;

    public Node(Figura data, double area) {
        this.data = data;
        this.area = area;
        this.next = null;
    }
    public Node (Figura data){
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
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node temp = this.head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    public double getArea(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node temp = this.head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.area;
    }

    public void add(Figura figura, double area) {
        Node newNode = new Node(figura, area);
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
            System.out.println("Rectangulo: " + temp.data + " Area: " + temp.area);
            temp = temp.next;
        }
    }

    public void delete(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
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
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node temp = this.head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        temp.data = data;
        temp.area = Rectangulo.area(data);
    }
}
