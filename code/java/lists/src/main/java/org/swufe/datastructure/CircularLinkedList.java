package org.swufe.datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A circularly linked list.
 */
public class CircularLinkedList<Item> implements Iterable<Item> {
    private static class Node<Item> {
        Item item;
        Node<Item> next;
        Node(Item item) {
            this(item, null);
        }

        Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }

    private Node<Item> tail; // we store tail (but not head)
    private int size;

    public CircularLinkedList() {
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Item first() {
        if (size == 0) {
            return null;
        }
        return tail.next.item;
    }

    public Item last() {
        if (size == 0) {
            return null;
        }
        return tail.item;
    }

    public void addFirst(Item item) {
        if (size == 0) {
            tail = new Node<>(item);
            tail.next = tail; // link to itself circularly
        } else {
            tail.next = new Node<>(item, tail.next);
        }
        size += 1;
    }

    public void addLast(Item item) {
        addFirst(item);
        tail = tail.next;
    }

    public void removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Node<Item> head = tail.next;
        if (head == tail) {
            // only one element
            tail = null;
        } else {
            tail.next = head.next;
        }
        size -= 1;
    }

    public void removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Node<Item> head = tail.next;
        if (head == tail) {
            // only one element
            tail = null;
        } else {
            // find the second node to last
            while (head.next != tail) {
                head = head.next;
            }
            head.next = tail.next;
            tail = head;
        }
        size -= 1;
    }

    // rotate the first element to the back of the list
    public void rotate() {
        if (tail != null) {
            tail = tail.next;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<Item> {
        Node<Item> node;
        int i;

        LinkedIterator() {
            this.i = 0;
            if (size > 0) {
                node = tail.next;
            } else {
                node = null;
            }
        }

        @Override
        public boolean hasNext() {
            return i < size;
        }

        @Override
        public Item next() {
            Item answer = node.item;
            node = node.next;
            i++;
            return answer;
        }
    }

}
