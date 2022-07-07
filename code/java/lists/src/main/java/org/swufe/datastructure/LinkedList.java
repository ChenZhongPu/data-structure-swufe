package org.swufe.datastructure;

public class LinkedList<Item> {
    private class Node {
        Item item;
        Node next;
    }

    private Node head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }


}
