package org.swufe;

import java.util.NoSuchElementException;

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

    public boolean isEmpty() {
        return size() == 0;
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
        if (key == null) throw new IllegalArgumentException();
        root = put(root, key);
    }


    private Node<Key> get(Node<Key> x, Key key) {
        if (x == null || x.key.compareTo(key) == 0) {
            return x;
        }
        if (key.compareTo(x.key) < 0) {
            return get(x.left, key);
        } else {
            return get(x.right, key);
        }
    }

    private Node<Key> get(Key key) {
        if (key == null) throw new IllegalArgumentException();
        return get(root, key);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    private Node<Key> removeMin(Node<Key> x) {
        // x != null
        if (x.left == null) return x.right;
        x.left = removeMin(x.left);
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void removeMin() {
        if (isEmpty()) throw new NoSuchElementException();
        root = removeMin(root);
    }

    public Node<Key> removeMax(Node<Key> x) {
        // x != null
        if (x.right == null) return x.left;
        x.right = removeMax(x.right);
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void removeMax() {
        if (isEmpty()) throw new NoSuchElementException();
        root = removeMax(root);
    }

    private Node<Key> delete(Node<Key> x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node<Key> t = x;
            x = min(t.right);
            x.right = removeMin(t.right);
            x.left = t.left;
        }
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void remove(Key key) {
        if (key == null) throw new IllegalArgumentException();
        root = delete(root, key);
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException();
        return min(root).key;
    }
    private Node<Key> min(Node<Key> x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    private Node<Key> max(Node<Key> x) {
        if (x.right == null) return x;
        else return max(x.right);
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException();
        return max(root).key;
    }

    private int height(Node<Key> x) {
        if (x == null) return -1;
        else return 1 + Math.max(height(x.left), height(x.right));
    }

    public int height() {
        return height(root);
    }
}
