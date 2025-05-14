public class Main {
    public static void main(String[] args) {
        BTree<Integer> bTree = new BTree<>();
        bTree.insert(5);
        bTree.insert(3);
        bTree.insert(7);
        bTree.insert(2);
        bTree.insert(4);
        bTree.insert(6);
        bTree.insert(8);

        System.out.println("Inorder traversal:");
        bTree.inorder();
        System.out.println("\nPreorder traversal:");
        bTree.preorder();
        System.out.println("\nPostorder traversal:");
        bTree.postorder();

        System.out.println("\n");
        bTree.printTree();
        System.out.println("\nEliminando 3:");
        bTree.delete(3);
        System.out.println("Inorder traversal after:");
        bTree.inorder();
        System.out.println("\nPreorder traversal after:");
        bTree.preorder();
        System.out.println("\n");
        bTree.printTree();
    }
}
