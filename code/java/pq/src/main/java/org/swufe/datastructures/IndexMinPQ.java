package org.swufe.datastructures;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class IndexMinPQ<Key extends Comparable<Key>> {
    private int maxN;
    private int n; // number of elements on PQ
    private int[] pq;
    private int[] reverse_pq;
    private Key[] keys;

    @SuppressWarnings("unchecked")
    public IndexMinPQ(int capacity) {
        this.maxN = capacity;
        n = 0;
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        reverse_pq = new int[maxN + 1];
        Arrays.fill(reverse_pq, -1);
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean contains(int i) {
        validateIndex(i);
        return reverse_pq[i] != -1;
    }

    public void insert(int i, Key key) {
        validateIndex(i);
        if (contains(i)) throw new IllegalArgumentException("Index has been used!");
        n++;
        pq[n] = i;
        reverse_pq[i] = n;
        keys[i] = key;
        swim(n);
    }

    public int minIndex() {
        if (isEmpty()) throw new NoSuchElementException();
        return pq[1];
    }

    public Key minKey() {
        if (isEmpty()) throw new NoSuchElementException();
        return keys[pq[1]];
    }

    public int delMin() {
        if (isEmpty()) throw new NoSuchElementException();
        int root = pq[1];
        swap(1, n--);
        sink(1);
        reverse_pq[root] = -1;
        keys[root] = null;
        pq[n+1] = -1;
        return root;
    }

    public Key keyOf(int i) {
        validateIndex(i);
        if (!contains(i)) throw new NoSuchElementException();
        else return keys[i];
    }

    public void changeKey(int i, Key key) {
        validateIndex(i);
        if (!contains(i)) throw new NoSuchElementException();
        keys[i] = key;
        swim(reverse_pq[i]);
        sink(reverse_pq[i]);
    }

    private void swap(int i, int j) {
        int t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
        reverse_pq[pq[i]] = i;
        reverse_pq[pq[j]] = j;
    }

    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            swap(k/2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            swap(k, j);
            k = j;
        }
    }

    private void validateIndex(int i) {
        if (i >= maxN || i < 0) throw new IllegalArgumentException();
    }
}
