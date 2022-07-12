package org.swufe.datastructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A singly linked list.
 */
public class LinkedList<Item> implements Iterable<Item> {
    private static class Node<Item> {
        Item item;
        Node<Item> next;
        Node(Item item) {
            this.item = item;
            next = null;
        }
    }

    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        Node<Item> node = new Node<>(item);
        node.next = head;
        head = node;
        if (tail == null) {
            tail = node;
        }
        size += 1;
    }

    public void addLast(Item item) {
        Node<Item> oldTail = tail;
        tail = new Node<>(item);
        if (oldTail == null) {
            head = tail;
        } else {
            oldTail.next = tail;
        }
        size += 1;
    }

    public void removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            head = head.next;
            if (head == null) {
                tail = null;
            }
            size -= 1;
        }
    }

    public void removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            if (head == tail) {
                // only one element
                head = null;
                tail = null;
            } else {
                // head -> ... -> walk -> tail
                Node<Item> walk = head;
                while (walk.next != tail) {
                    walk = walk.next;
                }
                walk.next = null;
                tail = walk;
            }
            size -= 1;
        }
    }

    public int indexOf(Item item) {
        int index = -1;
        Node<Item> walk = head;
        while (walk != null) {
            index += 1;
            if (walk.item.equals(item)) {
                return index;
            } else {
                walk = walk.next;
            }
        }
        return -1;
    }

    /**
     * Insert item at index i
     * @param i
     * @param item
     */
    public void insert(int i, Item item) {
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException();
        }
        if (i == 0) {
            addFirst(item);
        } else if (i == size) {
            addLast(item);
        } else {
            // before: head -> ... -> (i-1-th node: walk) -> (i-th node) -> ... -> tail
            // after: head -> ... -> (i-1-th node: walk) -> Node(item) -> (i-th node) -> ... -> tail
            Node<Item> walk = getNode(i - 1);
            Node<Item> ith = walk.next;
            Node<Item> node = new Node<>(item);
            walk.next = node;
            node.next = ith;
            size += 1;
        }
    }

    private Node<Item> getNode(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }

        int cnt = 0;
        Node<Item> walk = head;
        while (cnt < i) {
            walk = walk.next;
            cnt += 1;
        }

        return walk;
    }
    public Item get(int i) {
        Node<Item> walk = getNode(i);
        assert walk != null;
        return walk.item;
    }

    public void set(int i, Item item) {
        Node<Item> walk = getNode(i);
        assert walk != null;
        walk.item = item;
    }

    public void clear() {
        while (!isEmpty()) {
            removeFirst();
        }
    }

    /**
     * A utility method for debugging
     */
    public List<Item> toArrayList() {
        List<Item> results = new ArrayList<>();
        Node<Item> walk = head;
        while (walk != null) {
            results.add(walk.item);
            walk = walk.next;
        }
        return results;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Item> {
        private Node<Item> walk = head;
        @Override
        public boolean hasNext() {
            return walk != null;
        }

        @Override
        public Item next() {
            Item answer = walk.item;
            walk = walk.next;
            return answer;
        }
    }
}
