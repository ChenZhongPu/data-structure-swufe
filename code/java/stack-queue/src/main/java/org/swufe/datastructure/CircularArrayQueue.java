package org.swufe.datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A first-in, first-out (FIFO) data structure based on circular arrays
 */
public class CircularArrayQueue<Item> implements Iterable<Item> {
    private static final int DEFAULT_CAPACITY = 10;
    private Item[] a;
    private int n; // number of elements in queue
    private int front;

    @SuppressWarnings("unchecked")
    public CircularArrayQueue() {
        a = (Item[]) new Object[DEFAULT_CAPACITY];
        n = 0;
        front = 0;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void enqueue(Item item) {
        if (n == a.length) {
            resize(2 * a.length);
        }
        int avail = (front + n) % a.length;
        a[avail] = item;
        n += 1;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Dequeue from empty queue!");
        }
        if (n <= a.length / 4) {
            resize(a.length / 2);
        }
        Item answer = a[front];
        a[front] = null;
        n -= 1;
        front = (front + 1) % a.length;
        return answer;
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        assert capacity >= n;
        Item[] old = a;
        a = (Item[]) new Object[capacity];
        int walk = front;
        for (int i = 0; i < n; i++) {
            a[i] = old[walk];
            walk = (1 + walk) % old.length;
        }
        front = 0;
    }

    @Override
    public Iterator<Item> iterator() {
        return new CircularIterator();
    }

    private class CircularIterator implements Iterator<Item> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < n;
        }

        @Override
        public Item next() {
            int i = (index + front) % a.length;
            index += 1;
            return a[i];
        }
    }
}
