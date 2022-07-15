package org.swufe.datastructure;

/**
 * A circularly linked list
 */
public class CircularLinkedList<Item> {
    private static class Node<Item> {
        Item item;
        Node<Item> next;
        Node(Item item) {
            new Node<>(item, null);
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
}
