package org.swufe;

/**
 * A binary search tree with comparable keys
 */
public class BST<Key extends Comparable<Key>> {
    private static class Node<Key extends Comparable<Key>> {
        Key key;
        int n;
        Node<Key> left;
        Node<Key> right;

        public Node(Key key, int n, Node<Key> left, Node<Key> right) {
            this.key = key;
            this.n = n;
            this.left = left;
            this.right = right;
        }

        public Node(Key key) {
            this(key, 1,null, null);
        }
    }

    private Node<Key> root;

    public BST() {
        this.root = null;
    }

    private int size(Node<Key> node) {
        if (node == null) {
            return 0;
        } else {
            return node.n;
        }
    }

    public int size() {
        return size(root);
    }

    private Node<Key> put(Node<Key> x, Key key) {
        if (x == null) {
            return new Node<>(key);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key);
        else if (cmp > 0) x.right = put(x.right, key);
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void put(Key key) {
        root = put(root, key);
    }


    private Node<Key> get(Node<Key> x, Key key) {
        if (x == null || x.key.compareTo(key) == 0) {
            return x;
        }
        if (x.key.compareTo(key) < 0) {
            return get(x.left, key);
        } else {
            return get(x.right, key);
        }
    }

    public Node<Key> get(Key key) {
        return get(root, key);
    }
}
