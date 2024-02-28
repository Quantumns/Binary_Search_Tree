class AVLTree {
    TreeNode root;

    // Get the height of a node
    int height(TreeNode node) {
        if (node == null)
            return 0;
        return node.height;
    }

    // Get the balance factor of a node
    int balanceFactor(TreeNode node) {
        if (node == null)
            return 0;
        return height(node.right) - height(node.left);
    }

    // Perform right rotation
    TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Perform left rotation
    TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Insert a node into the AVL tree
    TreeNode insert(TreeNode node, int value) {
        // Perform standard BST insertion
        if (node == null)
            return new TreeNode(value);

        if (value < node.value)
            node.left = insert(node.left, value);
        else if (value > node.value)
            node.right = insert(node.right, value);
        else // Duplicate values not allowed
            return node;

        // Update height of current node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Get the balance factor and perform rotations if needed
        int balance = balanceFactor(node);

        // Left Left Case
        if (balance > 1 && value < node.left.value)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && value > node.right.value)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && value > node.left.value) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && value < node.right.value) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // If the node is balanced, return it unchanged
        return node;
    }

    // Check if the tree is AVL balanced
    boolean isAVLBalanced(TreeNode node) {
        if (node == null)
            return true;

        int balance = balanceFactor(node);
        if (Math.abs(balance) > 1)
            return false;

        return isAVLBalanced(node.left) && isAVLBalanced(node.right);
    }
}
