package org.swufe.datastructure;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CircularLinkedListTest {
    @Test
    void operator() {
        CircularLinkedList<String> list = new CircularLinkedList<>();
        list.addLast("structures");
        list.addLast("is");
        list.addLast("fun");
        list.addLast("!");
        assertEquals(list.size(), 4);
        assertEquals(list.first(), "structures");
        assertEquals(list.last(), "!");
        list.addFirst("data");

        list.removeFirst();
        list.removeFirst();
        list.removeFirst();
        list.removeFirst();
        assertEquals(list.size(), 1);
        list.removeFirst();
        assertTrue(list.isEmpty());

        list.addFirst("!");
        list.addFirst("fun");
        list.addFirst("is");
        list.addFirst("structures");
        list.addFirst("data");
        assertEquals(list.first(), "data");
        assertEquals(list.last(), "!");
        assertEquals(list.size(), 5);

        list.forEach(System.out::println);

        list.removeLast();
        assertEquals(list.last(), "fun");
        list.removeLast();
        assertEquals(list.last(), "is");
        list.removeLast();
        assertEquals(list.last(), "structures");
        list.removeLast();
        assertEquals(list.last(), "data");
        list.removeLast();
        assertThrows(NoSuchElementException.class, list::removeLast);

        list.addLast("structures");
        list.addLast("is");
        list.addLast("fun");
        list.addLast("!");
        list.rotate();
        assertEquals(list.last(), "structures");
    }
}