package Ejercicio_clase;

public class BolsaNum <N extends Number> extends Bolsa<N>{
    public void sort(){
        head = mergeSort(head);
    }
    private Node<N> mergeSort(Node<N> head){
        if(head == null || head.next == null) return head;
        Node<N> mid = getMid(head);
        Node<N> nextMid = mid.next;
        mid.next = null;

        Node<N> left = mergeSort(head), right = mergeSort(nextMid);

        return sortedMerge(left, right);
    }
    private Node<N> getMid(Node<N> head){
        if(head == null) return head;
        Node<N> slow = head, fast = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    private Node<N> sortedMerge(Node<N> left, Node<N> right){
        if(left == null) return right;
        if(right == null) return left;
        Node<N> result;
        if (left.data.doubleValue() <= right.data.doubleValue()){
            result = left;
            result.next = sortedMerge(left.next, right);
        } else {
            result = right;
            result.next = sortedMerge(left, right.next);
        }
        return result;
    }
}
