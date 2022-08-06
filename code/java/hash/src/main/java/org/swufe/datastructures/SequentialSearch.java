package org.swufe.datastructures;

import java.util.ArrayList;
import java.util.List;

/**
 * A linked list based implementation for sequential search
 */
public class SequentialSearch<Key, Value> {
    private int n;
    private Node<Key, Value> first;

    private static class Node<Key, Value> {
        private Key key;
        private Value value;
        private Node<Key, Value> next;

        public Node(Key key, Value value, Node<Key, Value> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public SequentialSearch() {
        n = 0;
        first = null;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException();
        for (Node<Key, Value> x = first; x != null; x = x.next) {
            if (x.key.equals(key)) return x.value;
        }
        return null;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException();
        return get(key) != null;
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException();
        if (value == null) {
            delete(key);
            return;
        }
        for (Node<Key, Value> x = first; x != null; x = x.next) {
            if (x.key.equals(key)) { // update
                x.value = value;
                return;
            }
        }
        // insert a new key-value pair
        first = new Node<>(key, value, first);
        n++;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException();
        Node<Key, Value> x = first;
        Node<Key, Value> y = null; // the node in front of x
        while (x != null) {
            if (x.key.equals(key)) {
                if (y == null) {
                    // x is first
                    first = first.next;
                } else {
                    y.next = x.next;
                }
                n--;
                return;
            }
            y = x;
            x = x.next;
        }
    }

    /**
     * Returns all keys in the symbol
     * @return
     */
    public Iterable<Key> keys() {
        List<Key> list = new ArrayList<>();
        for (Node<Key, Value> x = first; x != null; x = x.next) {
            list.add(x.key);
        }
        return list;
    }
}
