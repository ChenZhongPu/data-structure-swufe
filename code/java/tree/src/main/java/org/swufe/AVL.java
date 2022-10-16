package org.swufe;

import java.util.NoSuchElementException;

public class AVL <Key extends Comparable<Key>> {
    private static class Node<Key extends Comparable<Key>> {
        Key key;
        int height;
        Node<Key> left;
        Node<Key> right;

        Node(Key key) {
            this.key = key;
            height = 0;
        }
    }

    private Node<Key> root;

    public AVL() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private Node<Key> get(Node<Key> x, Key key) {
        if (x == null || key.compareTo(x.key) == 0) {
            return x;
        }
        if (key.compareTo(x.key) < 0) {
            return get(x.left, key);
        } else  {
            return get(x.right, key);
        }
    }

    public Node<Key> get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return get(root, key);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    private int getHeight(Node<Key> x) {
        if (x == null) return 0;
        return x.height;
    }

    public int getHeight() {
        return getHeight(root);
    }

    private int getBalanceFactor(Node<Key> x) {
        if (x == null) return 0;
        return getHeight(x.left) - getHeight(x.right);
    }

    private Node<Key> rotateRight(Node<Key> x) {
        assert x != null && x.left != null;
        Node<Key> y = x.left;
        x.left = y.right;
        y.right = x;
        x.height = Integer.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Integer.max(getHeight(y.left), getHeight(y.right)) + 1;
        return y;
    }

    private Node<Key> rotateLeft(Node<Key> y) {
        assert y != null && y.right != null;
        Node<Key> x = y.right;
        y.right = x.left;
        x.left = y;
        y.height = Integer.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Integer.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    private Node<Key> put(Node<Key> x, Key key) {
        if (x == null) {
            return new Node<>(key);
        }
        if (key.compareTo(x.key) < 0) {
            x.left = put(x.left, key);
        } else if (key.compareTo(x.key) > 0) {
            x.right = put(x.right, key);
        }
        x.height = Integer.max(getHeight(x.left), getHeight(x.right)) + 1;
        int bf = getBalanceFactor(x);
        if (bf > 1 && key.compareTo(x.left.key) < 0) {
            return rotateRight(x);
        }
        if (bf < -1 && key.compareTo(x.right.key) > 0) {
            return rotateLeft(x);
        }
        if (bf > 1 && key.compareTo(x.left.key) > 0) {
            x.left = rotateLeft(x.left);
            return rotateRight(x);
        }
        if (bf < -1 && key.compareTo(x.right.key) < 0) {
            x.right = rotateRight(x.right);
            return rotateLeft(x);
        }
        return x;
    }

    public void put(Key key) {
        root = put(root, key);
        assert isAVL();
    }

    public Node<Key> min(Node<Key> x) {
        assert x != null;
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException();
        return min(root).key;
    }

    private Node<Key> removeMin(Node<Key> x) {
        assert x != null;
        if (x.left == null) return x.right;
        x.left = removeMin(x.left);
        x.height = Integer.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    private Node<Key> remove(Node<Key> x, Key key) {
        if (x == null) return null;
        if (key.compareTo(x.key) < 0) {
            x.left = remove(x.left, key);
        } else if (key.compareTo(x.key) > 0) {
            x.right = remove(x.right, key);
        } else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node<Key> t = x;
            x = min(t.right);
            x.right = removeMin(t.right);
            x.left = t.left;
        }
        x.height = Integer.max(getHeight(x.left), getHeight(x.right)) + 1;
        int bf = getBalanceFactor(x);
        if (bf > 1 && getBalanceFactor(x.left) >= 0) {
            return rotateRight(x);
        }
        if (bf < -1 && getBalanceFactor(x.right) <= 0) {
            return rotateLeft(x);
        }
        if (bf > 1 && getBalanceFactor(x.left) < 0) {
            x.left = rotateLeft(x.left);
            return rotateRight(x);
        }
        if (bf < -1 && getBalanceFactor(x.right) > 0) {
            x.right = rotateRight(x.right);
            return rotateLeft(x);
        }
        return x;
    }

    public void remove(Key key) {
        root = remove(root, key);
        assert isAVL();
    }

    private boolean isAVL(Node<Key> x) {
        if (x == null) return true;
        return Math.abs(getBalanceFactor(x)) < 2 && isAVL(x.left) && isAVL(x.right);
    }

    private boolean isAVL() {
        return isAVL(root);
    }
}
