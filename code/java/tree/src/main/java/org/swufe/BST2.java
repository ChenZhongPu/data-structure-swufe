package org.swufe;

import java.util.NoSuchElementException;

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

    public boolean isEmpty() {
        return size == 0;
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

    private Node search(int key) {
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

    private Node min(Node x) {
        if (x == null) return null;
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    public int min() {
        if (isEmpty()) throw new NoSuchElementException();
        return min(root).key;
    }

    private Node max(Node x) {
        if (x == null) return null;
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    public int max() {
        if (isEmpty()) throw new NoSuchElementException();
        return max(root).key;
    }

    public boolean contains(int key) {
        return search(key) != null;
    }

    public void removeMin() {
//        if (isEmpty()) throw new NoSuchElementException();
//        Node x = root;
//        Node y = null;  // parent of x
//        while (x.left != null) { // descending till to the leftmost leaf
//            y = x;
//            x = x.left;
//        }
//        if (y == null) {
//            // x is the root, and remove it
//            root = x.right;
//        } else {
//            // remove x
//            y.left = x.right;
//        }
//        size--;
        root = removeMin(root);
    }

    private Node removeMin(Node x) {
        if (x == null) return null;
        // descending to the leftmost leaf
        Node z = x; // current node
        Node y = null; // parent of z
        while (z.left != null) {
            y = z;
            z = z.left;
        }
        if (y == null) {
            x = z.right;
        } else {
            y.left = z.right;
        }
        size--;
        return x;
    }

    public void removeMax() {
        if (isEmpty()) throw new NoSuchElementException();
        Node x = root;
        Node y = null; // y is the parent of x
        while (x.right != null) { // descending till to the rightmost leaf
            y = x;
            x = x.right;
        }
        if (y == null) {
            // x is root
            root = x.left;
        } else {
            y.right = x.left;
        }
        size--;
    }

    private void transplant(Node parent, Node u, Node v) {
        if (u == root) {
            root = v;
        } else if (u == parent.left) {
            parent.left = v;
        } else {
            parent.right = v;
        }
    }
    public void remove(int key) {
        Node x = root;
        Node parent = null;
        while (x != null && x.key != key) {
            parent = x;
            if (key < x.key) x = x.left;
            else x = x.right;
        }
        if (x != null) {
            if (x.right == null) {
                transplant(parent, x, x.left); // replace x by its left child
                size--;
            }
            else if (x.left == null) {
                transplant(parent, x, x.right); // replace x by its right child
                size--;
            }
            else {
                // if node has a reference to its parent
                // then the code can be simplified.
                Node t = x;
                x = min(t.right);
                x.right = removeMin(t.right);
                x.left = t.left;
                transplant(parent, t, x);
            }
        }
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

    private void inOrder(Node x) {
        if (x != null) {
            inOrder(x.left);
            System.out.println(x.key);
            inOrder(x.right);
        }
    }

    public void inOrder() {
        inOrder(root);
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

        bst2.removeMin();
        bst2.inOrder();
        System.out.println("----");
        bst2.removeMax();
        assert bst2.contains(19);
        n = bst2.search(19);
        assert n == null;
        bst2.inOrder();
        bst2.remove(12);
        assert bst2.isBST();
    }
}
