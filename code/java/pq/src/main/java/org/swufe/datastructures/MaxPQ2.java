package org.swufe.datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class MaxPQ2<Key> {
    private ArrayList<Key> pq;
    private Comparator<Key> comparator;

    public MaxPQ2() {
        this(null);
    }

    public MaxPQ2(Comparator<Key> comparator) {
        pq = new ArrayList<>();
        pq.add(null);
        this.comparator = comparator;
    }

    public int size() {
        return pq.size() - 1;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    @SuppressWarnings("unchecked")
    private boolean less(int i, int j) {
        if (comparator != null) {
            return comparator.compare(pq.get(i), pq.get(j)) < 0;
        } else {
           return ((Comparable<Key>) pq.get(i)).compareTo(pq.get(j)) < 0;
        }
    }

    private void swap(int i, int j) {
        Collections.swap(pq, i, j);
    }

    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            swap(k/2, k);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= size()) {
            int j = 2 * k;
            if (j < size() && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            swap(k, j);
            k = j;
        }
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException();
        return pq.get(1);
    }

    public void insert(Key v) {
        pq.add(v);
        swim(size());
        assert isMaxHeap();
    }

    public Key delMax() {
        if (isEmpty()) throw new NoSuchElementException();
        Key root = pq.get(1);
        swap(1, size());
        pq.remove(size());
        sink(1);
        assert isMaxHeap();
        return root;
    }

    private boolean isMaxHeapOrdered(int k) {
        if (k > size()) return true;
        int left = 2 * k;
        int right = left + 1;
        if (left <= size() && less(k, left)) return false;
        if (right <= size() && less(k, right)) return false;
        return isMaxHeapOrdered(left) && isMaxHeapOrdered(right);
    }

    private boolean isMaxHeap() {
        if (pq.get(0) != null) return false;
        return isMaxHeapOrdered(1);
    }
}
