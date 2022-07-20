package org.swufe.datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A basic doubly linked list.
 */
public class DoublyLinkedList<Item> implements Iterable<Item> {
    private static class Node<Item> {
        Item item;
        Node<Item> prev;
        Node<Item> next;

        public Node(Item item, Node<Item> prev, Node<Item> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private final Node<Item> header;
    private final Node<Item> trailer;
    private int size;

    public DoublyLinkedList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null); // trailer is preceded by header
        header.next = trailer; // header is followed by trailer
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Item first() {
        if (isEmpty()) return null;
        else return header.next.item;
    }

    public Item last() {
        if (isEmpty()) return null;
        else return trailer.prev.item;
    }

    private void addBetween(Item item, Node<Item> predecessor, Node<Item> successor) {
        Node<Item> node = new Node<>(item, predecessor, successor);
        predecessor.next = node;
        successor.prev = node;
        size++;
    }

    public void addFirst(Item item) {
        addBetween(item, header, header.next);
    }

    public void addLast(Item item) {
        addBetween(item, trailer.prev, trailer);
    }

    private void remove(Node<Item> node) {
        Node<Item> predecessor = node.prev;
        Node<Item> successor = node.next;
        predecessor.next = successor;
        successor.prev = predecessor;
        size--;
    }

    public void removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        remove(header.next);
    }

    public void removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        remove(trailer.prev);
    }

    @Override
    public Iterator<Item> iterator() {
        return new DoublyIterator();
    }

    private class DoublyIterator implements Iterator<Item> {
        Node<Item> node = header.next;
        @Override
        public boolean hasNext() {
            return node != trailer;
        }

        @Override
        public Item next() {
            Item answer = node.item;
            node = node.next;
            return answer;
        }
    }
}
