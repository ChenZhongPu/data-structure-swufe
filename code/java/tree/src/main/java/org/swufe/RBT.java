package org.swufe;
/**
 * A red-black tree.
 */
public class RBT<Key extends Comparable<Key>> {

    private static final boolean RED   = false;
    private static final boolean BLACK = true;

    private static class Node<Key extends Comparable<Key>> {
        Key key;
        boolean color;
        Node<Key> left;
        Node<Key> right;
        Node<Key> p;

        public Node(Key key) {
            this.key = key;
            color = RED;
            left = null;
            right = null;
            p = null;
        }
    }

    private Node<Key> root;
    private int size;

    public RBT() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void rotateLeft(Node<Key> x) {
        assert x.right != null;
        Node<Key> y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.p = x;
        }
        y.p = x.p;
        if (x.p == null) {
            root = y;
        } else if (x == x.p.left) {
            x.p.left = y;
        } else {
            x.p.right = y;
        }
        y.left = x;
        x.p = y;
    }

    private void rotateRight(Node<Key> y) {
        assert y.left != null;
        Node<Key> x = y.left;
        y.left = x.right;
        if (x.right != null) {
            x.right.p = y;
        }
        x.p = y.p;
        if (y.p == null) {
            root = x;
        } else if (y == y.p.left) {
            y.p.left = x;
        } else {
            y.p.right = x;
        }
        x.right = y;
        y.p = x;
    }

    private boolean colorOf(Node<Key> x) {
        return x == null ? BLACK : x.color;
    }

    private void setColor(Node<Key> x, boolean c) {
        if (x != null) {
            x.color = c;
        }
    }

    public void insertFixup(Node<Key> z) {
        assert z.color == RED;
        while (colorOf(z.p) == RED) {
            if (z.p == z.p.p.left) { // is z's parent a left child
                Node<Key> y = z.p.p.right; // y is z's uncle
                if (colorOf(y) == RED) { // are z's parent and uncle both red?
                    // case 1
                    setColor(z.p, BLACK);
                    setColor(y, BLACK);
                    setColor(z.p.p, RED);
                    z = z.p.p;
                } else {
                    if (z == z.p.right) {
                        // case 2
                        z = z.p;
                        rotateLeft(z);
                    }
                    // case 3
                    setColor(z.p, BLACK);
                    setColor(z.p.p, RED);
                    rotateRight(z.p.p);
                }
            } else { // same as above with "right" and "left" exchanged
                Node<Key> y = z.p.p.left;
                if (colorOf(y) == RED) {
                    setColor(z.p, BLACK);
                    setColor(y, BLACK);
                    setColor(z.p.p, RED);
                    z = z.p.p;
                } else {
                    if (z == z.p.left) {
                        z = z.p;
                        rotateRight(z);
                    }
                    setColor(z.p, BLACK);
                    setColor(z.p.p, RED);
                    rotateLeft(z.p.p);
                }
            }
        }
        root.color = BLACK;
    }

    public void put(Key key) {
        if (key == null) {
            throw new NullPointerException();
        }
        Node<Key> x = root; // node being compared with z
        if (x == null) {
            root = new Node<>(key);
            size = 1;
            root.color = BLACK;
            return;
        }
        Node<Key> y = null; // y will be parent of z
        int cmp;
        while (x != null) { // descend until reaching the sentinel
            y = x;
            cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return; // stop if duplicated keys are found
            }
        }
        Node<Key> z = new Node<>(key);
        z.p = y; // found the location: insert z with parent y (y != null)
        size++;
        cmp = key.compareTo(y.key);
        if (cmp < 0) {
            y.left = z;
        } else {
            y.right = z;
        }
        insertFixup(z);
    }

    private Node<Key> get(Key key) {
        if (key == null) {
            throw new NullPointerException();
        }
        Node<Key> x = root;
        while (x != null && key.compareTo(x.key) != 0) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return x;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    private int height(Node<Key> x) {
        if (x == null) return -1;
        else return 1 + Math.max(height(x.left), height(x.right));
    }

    public int height() {
        return height(root);
    }
}
