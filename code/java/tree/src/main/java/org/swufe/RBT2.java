package org.swufe;

/**
 * Red-black tree with deletion operation
 */
public class RBT2 <Key extends Comparable<Key>>{
    private static final boolean RED   = false;
    private static final boolean BLACK = true;

    private static class Node<Key extends Comparable<Key>> {
        Key key;
        boolean color;
        Node<Key> left;
        Node<Key> right;
        Node<Key> p;

        Node(Key key) {
            this.key = key;
        }

        Node() {
            this.key = null;
            color = BLACK;
            left = null;
            right = null;
            p = null;
        }

    }

    private Node<Key> root;
    private final Node<Key> NIL = new Node<>();

    private int size;

    public RBT2() {
        root = NIL;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private Node<Key> get(Key key) {
        Node<Key> x = root;
        while (x != NIL && key.compareTo(x.key) != 0) {
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
        if (key == null) throw new NullPointerException();
        return get(key) != NIL;
    }

    private void leftRotate(Node<Key> x) {
        Node<Key> y = x.right;
        x.right = y.left;
        if (y.left != NIL) {
            y.left.p = x;
        }
        y.p = x.p;
        if (x.p == NIL) {
            root = y;
        } else if (x == x.p.left) {
            x.p.left = y;
        } else {
            x.p.right = y;
        }
        y.left = x;
        x.p = y;
    }

    private void rightRotate(Node<Key> y) {
        Node<Key> x = y.left;
        y.left = x.right;
        if (x.right != NIL) {
            x.right.p = y;
        }
        x.p = y.p;
        if(y.p == NIL) {
            root = x;
        } else if (y == y.p.left) {
            y.p.left = x;
        } else {
            y.p.right = x;
        }
        x.right = y;
        y.p = x;
    }

    public void put(Key key) {
        Node<Key> x = root;
        Node<Key> y = NIL;
        while (x != NIL) {
            y = x;
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return;
            }
        }
        Node<Key> z = new Node<>(key);
        z.color = RED;
        z.p = y;
        if (y == NIL) {
            root = z;
        } else if (z.key.compareTo(y.key) < 0) {
            y.left = z;
        } else {
            y.right = z;
        }
        z.left = NIL;
        z.right = NIL;
        size++;
        insertFixup(z);
    }

    private void insertFixup(Node<Key> z) {
        while (z.p.color == RED) {
            if (z.p == z.p.p.left) {
                Node<Key> y = z.p.p.right;
                if (y.color == RED) {
                    // case 1
                    z.p.color = BLACK;
                    y.color = BLACK;
                    z.p.p.color = RED;
                    z = z.p.p;
                } else {
                    if (z == z.p.right) {
                        // case 2
                        z = z.p;
                        leftRotate(z);
                    }
                    // case 3
                    z.p.color = BLACK;
                    z.p.p.color = RED;
                    rightRotate(z.p.p);
                }
            } else {
                Node<Key> y = z.p.p.left;
                if (y.color == RED) {
                    z.p.color = BLACK;
                    y.color = BLACK;
                    z.p.p.color = RED;
                    z = z.p.p;
                } else {
                    if (z == z.p.left) {
                        z = z.p;
                        rightRotate(z);
                    }
                    z.p.color = BLACK;
                    z.p.p.color = RED;
                    leftRotate(z.p.p);
                }
            }
        }
        root.color = BLACK;
    }

    private Node<Key> min(Node<Key> x) {
        while(x.left != NIL) {
            x = x.left;
        }
        return x;
    }

    private void transplant(Node<Key> u, Node<Key> v) {
        if (u.p == NIL) {
            root = v;
        } else if (u == u.p.left) {
            u.p.left = v;
        } else {
            u.p.right = v;
        }
        v.p = u.p;
    }

    public void remove(Key key) {
        if (key == null) throw new NullPointerException();
        Node<Key> z = get(key);
        if (z != NIL) {
            delete(z);
            size--;
        }
    }

    private void delete(Node<Key> z) {
        if (z.left != NIL && z.right != NIL) {
            Node<Key> y = min(z.right);
            z.key = y.key;
            z = y;
        }
        Node<Key> x = (z.left != null) ? z.left : z.right;
        transplant(z, x);
        deleteFixup(x);
    }

    private void deleteFixup(Node<Key> x) {
        while(x != root && x.color == BLACK) {
            if (x == x.p.left) {
                Node<Key> w = x.p.right;
                if (w.color == RED) {
                    w.color = BLACK;
                    x.p.color = RED;
                    leftRotate(x.p);
                    w = x.p.right;
                }
                if (w.left.color == BLACK && w.right.color == BLACK) {
                    w.color = RED;
                    x = x.p;
                } else {
                    if (x.right.color == BLACK) {
                        w.left.color = BLACK;
                        w.color = RED;
                        rightRotate(w);
                        w = x.p.right;
                    }
                    w.color = x.p.color;
                    x.p.color = BLACK;
                    w.right.color = BLACK;
                    leftRotate(x.p);
                    x = root;
                }
            } else {
                Node<Key> w = x.p.left;
                if (w.color == RED) {
                    w.color = BLACK;
                    x.p.color = RED;
                    rightRotate(x.p);
                    w = x.p.left;
                }
                if (w.right.color == BLACK && w.left.color == BLACK) {
                    w.color = RED;
                    x = x.p;
                } else {
                    if (w.left.color == BLACK) {
                        w.right.color = BLACK;
                        w.color = RED;
                        leftRotate(w);
                        w = x.p.left;
                    }
                    w.color = x.p.color;
                    x.p.color = BLACK;
                    x.left.color = BLACK;
                    rightRotate(x.p);
                    x = root;
                }
            }
        }
        x.color = BLACK;
    }

    private int height(Node<Key> x) {
        if (x == NIL) return -1;
        else return 1 + Math.max(height(x.left), height(x.right));
    }

    public int height() {
        return height(root);
    }

    public boolean isRBT() {
        if (!isBST()) System.err.println("Not a binary search tree");
        if (root.color != BLACK) System.err.println("The root is not black");
        if (!isBalanced()) System.err.println("Not balanced");
        return isBST() && root.color == BLACK && isBalanced();
    }

    private boolean isBST() {
        return isBST(root, null, null);
    }
    private boolean isBST(Node<Key> x, Key min, Key max) {
        if (x == NIL) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    private boolean isBalanced() {
        int black = 0;
        Node<Key> x = root;
        while (x != NIL) {
            if (x.color == BLACK) black++;
            x = x.left;
        }
        return isBalanced(root, black);
    }

    // does every path from the root to a leaf have the given number of black links?
    private boolean isBalanced(Node<Key> x, int black) {
        if (x == NIL) return black == 0;
        if (x.color == BLACK) black--;
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    }
}
