package org.swufe.datastructure;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A last-in, first-out (LIFO) data structure.
 */
public class ArrayStack<Item> implements Iterable<Item> {
    private static final int INIT_CAPACITY = 10;

    private Item[] a;
    private int n; // number of elements on stack

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        a = (Item[]) new Object[INIT_CAPACITY];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= n;
        a = Arrays.copyOf(a, capacity);
    }

    public void push(Item item) {
        if (n == a.length) resize(2 * a.length);
        a[n++] = item;
    }

    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = a[n-1];
        a[n-1] = null;
        n--;
        // shrink size of array if necessary
        if (n > 0 && n == a.length / 4) resize(a.length / 2);
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
       return new ReverseArrayIterator();
    }


    private class ReverseArrayIterator implements Iterator<Item> {
        private int i;

        public ReverseArrayIterator() {
            i = n - 1;
        }

        @Override
        public boolean hasNext() {
            return i >= 0;
        }

        @Override
        public Item next() {
            return a[i--];
        }
    }
}
