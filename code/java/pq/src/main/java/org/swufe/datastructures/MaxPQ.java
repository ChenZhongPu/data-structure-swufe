package org.swufe.datastructures;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MaxPQ<Key extends Comparable<Key>> {

    private Key[] pq;
    private int n;

    @SuppressWarnings("unchecked")
    public MaxPQ() {
        int INIT_CAPACITY = 16;
        pq = (Key[]) new Comparable[INIT_CAPACITY + 1];
        n = 0;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private void swap(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            swap(k/2, k);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j+1)) j++;
            if (!less(k, j)) break;
            swap(k, j);
            k = j;
        }
    }

    private void resize(int capacity) {
        assert capacity > n;
        pq = Arrays.copyOf(pq, capacity);
    }

    public void insert(Key key) {
        if (n == pq.length - 1) resize(2 * pq.length);
        pq[++n] = key;
        swim(n);
        assert isMaxHeap();
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException();
        return pq[1];
    }

    public Key delMax() {
        if (isEmpty()) throw new NoSuchElementException();
        Key root = pq[1];
        swap(1, n--);
        sink(1);
        pq[n+1] = null;
        if (n > 0 && n == (pq.length - 1) / 4) resize((pq.length - 1) / 2);
        assert isMaxHeap();
        return root;
    }

    // is the heap rooted at k a max heap?
    private boolean isMaxHeapOrdered(int k) {
        if (k > n) return true;
        int left = 2 * k;
        int right = left + 1;
        if (left <= n && less(k, left)) return false;
        if (right <= n && less(k, right)) return false;
        return isMaxHeapOrdered(left) && isMaxHeapOrdered(right);
    }

    private boolean isMaxHeap() {
        // 1..=n is not null
        for (int i = 1; i <= n; i++) {
            if (pq[i] == null) return false;
        }
        // n+1..length is null
        for (int i = n + 1; i < pq.length; i++) {
            if (pq[i] != null) return false;
        }
        if (pq[0] != null) return false;
        return isMaxHeapOrdered(1);
    }
}
