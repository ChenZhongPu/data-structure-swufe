package org.swufe.datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A stack based on singly linked lists.
 */
public class LinkedStack<Item> implements Iterable<Item> {
    private static class Node<Item> {
        Item item;
        Node<Item> next;

        Node(Item item) {
            this.item = item;
            next = null;
        }
    }

    private Node<Item> head;
    private int size;

    public LinkedStack() {
        head = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // push() = add_first()
    public void push(Item item) {
        Node<Item> node = new Node<>(item);
        node.next = head;
        head = node;
        size += 1;
    }

    // pop() = remove_first()
    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Pop from an empty stack");
        }
        Item answer = head.item;
        head = head.next;
        size -= 1;
        return answer;
    }

    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Peek from an empty stack");
        }
        return head.item;
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
