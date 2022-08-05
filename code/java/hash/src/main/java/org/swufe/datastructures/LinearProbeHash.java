package org.swufe.datastructures;

public class LinearProbeHash<Key, Value> {

    private static final int INIT_CAPACITY = 4; // 2's power

    private Key[] keys;
    private Value[] values;

    private int n; // number of key-value pairs
    private int m; // size of hash table

    @SuppressWarnings("unchecked")
    public LinearProbeHash(int cap) {
        keys = (Key[]) new Object[cap];
        values = (Value[]) new Object[cap];
        m = cap;
        n = 0;
    }

    public LinearProbeHash() {
        this(INIT_CAPACITY);
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }
    private int hash(Object x) {
        int h = x.hashCode();
        h ^= (h >>> 16);
        return h & (m - 1);
    }

    public void put(Key k, Value v) {
        if (k == null) {
            throw new IllegalArgumentException();
        }
        if (v == null) {
            delete(k);
            return;
        }
        if (n >= m/2) {
            resize(2 * m);
        }
        int i;
        for (i = hash(k); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(k)) {
                values[i] = v;
                return;
            }
        }
        keys[i] = k;
        values[i] = v;
        n++;
    }

    public Value get(Key k) {
        for (int i = hash(k); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(k)) {
                return values[i];
            }
        }
        return null;
    }

    public boolean contains(Key k) {
        if (k == null) throw new IllegalArgumentException();
        return get(k) != null;
    }

    public void delete(Key k) {
        if (!contains(k)) return;
        // find the position of `k`
        int i = hash(k);
        while (!k.equals(keys[i])) {
            i = (i + 1) % m;
        }
        // delete key and associated value
        keys[i] = null;
        values[i] = null;
        // rehash all keys in the same cluster
        i = (i + 1) % m;
        while (keys[i] != null) {
            Key keyToRehash = keys[i];
            Value valueToRehash = values[i];
            keys[i] = null;
            values[i] = null;
            n--;
            put(keyToRehash, valueToRehash);
            i = (i + 1) % m;
        }
        n--;
        if (n > 0 && n <= m / 8) resize(m / 2);
    }
    private void resize(int cap) {
        LinearProbeHash<Key, Value> t = new LinearProbeHash<>(cap);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                t.put(keys[i], values[i]);
            }
        }
        keys = t.keys;
        values = t.values;
        m = t.m;
    }
}
