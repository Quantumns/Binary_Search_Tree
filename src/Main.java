public class Main {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // Insert elements into the tree
        int[] elements = {45, 27, 67, 36, 56, 15, 75, 31, 53, 39, 64};
        for (int element : elements) {
            tree.root = tree.insert(tree.root, element);
        }

        // Check if the tree is AVL balanced
        boolean isAVLBalanced = tree.isAVLBalanced(tree.root);
        System.out.println("Is AVL balanced: " + isAVLBalanced);
    }
}
