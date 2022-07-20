package org.swufe.datastructure;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {
    @Test
    void operation() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.addFirst("fun");
        list.addFirst("is");
        list.addFirst("structures");
        list.addFirst("data");
        assertEquals(list.size(), 4);
        assertEquals(list.first(), "data");
        assertEquals(list.last(), "fun");

        list.removeFirst();
        list.removeLast();
        assertEquals(list.size(), 2);
        assertEquals(list.first(), "structures");
        assertEquals(list.last(), "is");

        list.addLast("really");
        list.addLast("fun");
        assertEquals(list.last(), "fun");
        list.forEach(System.out::println);

        list.removeLast();
        list.removeLast();
        list.removeFirst();
        list.removeFirst();
        assertTrue(list.isEmpty());
        assertThrows(NoSuchElementException.class, list::removeFirst);
    }
}