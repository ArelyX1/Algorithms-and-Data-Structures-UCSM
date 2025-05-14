public class BtreeDemo <T extends Comparable<T>> {
    Node <T> root;
    public BtreeDemo() {
        this.root = null;
    }
    public void insert(T dato){
        root = insertRec(root, dato);
    }
    public Node<T> insertRec(Node<T> root, T dato){
        if (root == null){
            root = new Node<T>(dato);
            return root;
        }
        if (dato.compareTo(root.dato) < 0){
            root.leftNode = insertRec(root.leftNode, dato);
        } else if (dato.compareTo(root.dato) > 0){
            root.rightNode = insertRec(root.rightNode, dato);
        }
        return root;
    }
    public void inorder(){
        inorderRec(root);
    }
    public void inorderRec(Node<T> root){
        if (root != null){
            inorderRec(root.leftNode);
            System.out.print(root.dato + " ");
            inorderRec(root.rightNode);
        }
    }
}
