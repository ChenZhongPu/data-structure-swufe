package org.swufe;

import java.util.NoSuchElementException;

/**
 * A binary search tree based on BST2 with the parent pointer
 */
public class BST3 {
    private static class Node {
        int key;
        Node left;
        Node right;
        Node parent;
        Node(int key) {
            this(key, null);
        }

        Node(int key, Node parent) {
            this.key = key;
            this.parent = parent;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST3() {
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
        Node x = root;
        Node y = null; // y is z's parent
        while (x != null) {
            y = x;
            if (key < x.key) {
                x = x.left;
            } else if (key > x.key) {
                x = x.right;
            } else {
                return;
            }
        }
        Node z = new Node(key, y); // y is z's parent
        size++;
        if (y == null) {
            root = z;
        } else if (z.key < y.key) {
            y.left = z;
        } else {
            y.right = z;
        }
    }

    private Node get(int key) {
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

    public boolean contains(int key) {
        return get(key) != null;
    }

    private void transplant(Node u, Node v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        if (v != null) {
            v.parent = u.parent;
        }
    }

    private void delete2(Node z) {
        if (z.left != null && z.right != null) {
            Node y = min(z.right); // y is z's successor
            z.key = y.key;
            z = y;
        }
        // z has at most one child
        if (z.left == null) {
            transplant(z, z.right);
        } else {
            transplant(z, z.left);
        }
    }

    private void delete(Node z) {
        if (z.left == null) {
            transplant(z, z.right);
        } else if (z.right == null) {
            transplant(z, z.left);
        } else {
            Node y = min(z.right);
            if (y != z.right) {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
        }
    }

    public void remove(int key) {
        Node z = get(key);
        if (z != null) {
            delete2(z);
            size--;
        }
    }

    private Node min(Node x) {
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
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    public int max() {
        if (isEmpty()) throw new NoSuchElementException();
        return max(root).key;
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
        BST3 bst3 = new BST3();
        bst3.put(12);
        bst3.put(5);
        bst3.put(2);
        bst3.put(9);
        bst3.put(18);
        bst3.put(19);
        bst3.put(9);
        bst3.put(15);
        bst3.put(17);
        bst3.put(13);
        //           12
        //         /    \
        //       5      18
        //     /  \    /  \
        //    2    9  15  19
        //            / \
        //          13   17
        bst3.inOrder();
        assert bst3.size() == 9;
        assert bst3.isBST();

        assert bst3.contains(9);
        assert !bst3.contains(0);

        bst3.remove(15);
        assert !bst3.contains(15);

        assert bst3.isBST();
        System.out.println("----");
        bst3.inOrder();

        bst3.put(20);
        bst3.put(21);
        bst3.put(22);
        bst3.remove(18);
        assert bst3.isBST();
    }
}
