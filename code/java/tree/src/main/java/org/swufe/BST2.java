package org.swufe;

/**
 * A binary search tree that mainly uses iterative algorithms.
 *
 * For simplicity, this implementation always assumes that key is an integer,
 * so we didn't use generics.
 */
public class BST2 {
    private static class Node {
        int key;
        Node left;
        Node right;

        public Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }

        public Node(int key) {
            this(key, null, null);
        }
    }

    private Node root;
    private int size;

    private BST2() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public void put(int key) {
        Node x = root; // node being compared with key
        Node y = null; // y will be parent of z
        while (x != null) { // descend until reaching a leaf
            y = x;
            if (key < x.key) {
                x = x.left;
            } else if (key > x.key ){
                x = x.right;
            } else {
                return; // stop
            }
        }
        Node z = new Node(key);
        if (y == null) {
            root = z;
        } else if (key < y.key) {
            y.left = z;
        } else {
            y.right = z;
        }
        size++;
    }

    public Node search(int key) {
        Node x = root;
        while (x != null && key != x.key) {
            if (key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return x;
    }

    public boolean isBST() {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private boolean isBST(Node x, int min, int max) {
        if (x == null) return true;
        if (x.key <= min) return false;
        if (x.key >= max) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    public static void main(String[] args) {
        BST2 bst2 = new BST2();
        bst2.put(12);
        bst2.put(5);
        bst2.put(2);
        bst2.put(9);
        bst2.put(18);
        bst2.put(19);
        assert bst2.isBST();

        bst2.put(15);
        bst2.put(17);
        bst2.put(13);
        assert bst2.isBST();

        Node n = bst2.search(15);
        assert n.key == 15;
    }
}
