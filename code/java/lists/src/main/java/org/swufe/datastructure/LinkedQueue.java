package org.swufe.datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A queue based on singly linked lists.
 */
public class LinkedQueue<Item> implements Iterable<Item> {
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

    public LinkedQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Peek from an empty queue");
        return head.item;
    }

    // enqueue() = add_last()
    public void enqueue(Item item) {
        Node<Item> oldTail = tail;
        tail = new Node<>(item);
        if (isEmpty()) head = tail;
        else oldTail.next = tail;
        size += 1;
    }

    // dequeue() = remove_first()
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Peek from an empty queue");
        Item answer = head.item;
        head = head.next;
        if (isEmpty()) {
            tail = null;
        }
        size -= 1;
        return answer;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> node = head;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Item next() {
            Item answer = node.item;
            node = node.next;
            return answer;
        }
    }
}
