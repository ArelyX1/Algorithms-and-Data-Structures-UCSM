public class BTree <T extends Comparable<T>> {
    Node<T> root;

    public BTree() {
        this.root = null;
    }

    public void insert(T dato) {
        root = insertRec(root, dato);
    }

    private Node<T> insertRec(Node<T> root, T dato) {
        if (root == null) {
            root = new Node<>(dato);
            return root;
        }
        if (dato.compareTo(root.dato) < 0) {
            root.leftNode = insertRec(root.leftNode, dato);
        } else if (dato.compareTo(root.dato) > 0) {
            root.rightNode = insertRec(root.rightNode, dato);
        }
        return root;
    }

    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(Node<T> root) {
        if (root == null) return;
        inorderRec(root.leftNode);
        System.out.print(root.dato + " ");
        inorderRec(root.rightNode);
    }

    public void preorder() {
        preorderRec(root);
    }
    
    private void preorderRec(Node<T> root) {
        if (root == null) return;
        System.out.print(root.dato + " ");
        preorderRec(root.leftNode);
        preorderRec(root.rightNode);
    }

    public void postorder() {
        postorderRec(root);
    }

    private void postorderRec(Node<T> root) {
        if (root == null) return; 
        postorderRec(root.leftNode);
        postorderRec(root.rightNode);
        System.out.print(root.dato + " ");
        
    }

    public void delete(T dato) {
        root = deleteRec(root, dato);
    }
    private Node<T> deleteRec(Node<T> root, T dato) {
        if (root == null) {
            return root;
        }
        if (dato.compareTo(root.dato) < 0) {
            root.leftNode = deleteRec(root.leftNode, dato);
        } else if (dato.compareTo(root.dato) > 0) {
            root.rightNode = deleteRec(root.rightNode, dato);
        } else {
            if (root.leftNode == null) {
                //* Caso 1: Si tiene un hijo izquierdo o no tiene ningun hijo
                return root.rightNode;
            } else if (root.rightNode == null) { 
                //* Caso 2: Si tiene un hijo derecho e izquierdo o no tiene hijo derecho
                return root.leftNode;
            }
            root.dato = minValue(root.rightNode);
            root.rightNode = deleteRec(root.rightNode, root.dato);
        }
        return root;
    }

    private T minValue(Node<T> root) {
        T minv = root.dato;
        while (root.leftNode != null) {
            minv = root.leftNode.dato;
            root = root.leftNode;
        }
        return minv;
    }
    public void printTree() {
        printTreeRec(root, "", true);
    }

    private void printTreeRec(Node<T> node, String prefix, boolean isLeft) {
        if (node == null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + "null");
            return;
        }
        System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.dato);
        printTreeRec(node.leftNode, prefix + (isLeft ? "│   " : "    "), true);
        printTreeRec(node.rightNode, prefix + (isLeft ? "│   " : "    "), false);
}

}
