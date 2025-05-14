public class Demo {
    public static void main(String[] args) {
        BtreeDemo<Integer> bTree = new BtreeDemo<>();
        bTree.insert(5);
        bTree.insert(3);
        bTree.insert(7);
        bTree.insert(2);
        bTree.insert(4);
        bTree.insert(6);
        bTree.insert(8);

        System.out.println("Inorder traversal of the binary tree:");
        bTree.inorder();
        
    }
}
