package org.swufe.datastructures;

public class SeparateChainingHash<Key, Value> {

    private static final int INIT_CAPACITY = 4;
    private int m;
    private int n;
    private SequentialSearch<Key, Value>[] table;

    @SuppressWarnings("unchecked")
    public SeparateChainingHash(int cap) {
        m = cap;
        n = 0;
        table = (SequentialSearch<Key, Value>[]) new SequentialSearch[cap];
        for (int i = 0; i < m; i++) {
            table[i] = new SequentialSearch<>();
        }
    }

    public SeparateChainingHash() {
        this(INIT_CAPACITY);
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private int hash(Key key) {
        int h = key.hashCode();
        h ^= (h >>> 16);
        return h & (m - 1);
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException();
        if (value == null) {
            delete(key);
            return;
        }
        if (n >= 10 * m) resize(2 * m);

        int i = hash(key);
        if (!table[i].contains(key)) n++;
        table[i].put(key, value);
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException();
        int i = hash(key);
        return table[i].get(key);
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException();
        return get(key) != null;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException();

        int i = hash(key);
        if (table[i].contains(key)) n--;
        table[i].delete(key);

        if (m > INIT_CAPACITY && n <= 2 * m) resize(m / 2);
    }

    private void resize(int cap) {
        SeparateChainingHash<Key, Value> t = new SeparateChainingHash<>();
        for (int i = 0; i < m; i++) {
            for (Key key : table[i].keys()) {
                // it will boost the performance
                // if key and its associated value are returned
                t.put(key, table[i].get(key));
            }
        }
        m = t.m;
        table = t.table;
    }

}
