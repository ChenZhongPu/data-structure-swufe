package org.swufe.datastructure;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A last-in, first-out (LIFO) data structure.
 */
public class ArrayListStack<Item> implements Iterable<Item> {
    /**
     * An iterator for a stack by reversing an array list
     */
    private class ReverseArrayListIterator implements Iterator<Item> {
        private int i = elements.size();
        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return elements.get(--i);
        }
    }
    private final List<Item> elements;

    public ArrayListStack() {
        elements = new ArrayList<>();
    }

    public void push(Item item) {
        elements.add(item);
    }

    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Pop from empty stack!");
        }
        Item v = elements.get(size() - 1);
        elements.remove(size() - 1);
        return v;
    }

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayListIterator();
    }
}

