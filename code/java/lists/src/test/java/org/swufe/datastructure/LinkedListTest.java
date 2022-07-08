package org.swufe.datastructure;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    @Test
    void operation() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("fun");
        list.addFirst("is");
        list.addFirst("structures");
        list.addFirst("data");
        assertEquals(list.size(), 4);
        assertEquals(list.indexOf("fun"), 3);
        assertEquals(list.get(0), "data");
        list.removeFirst();
        assertEquals(list.get(0), "structures");
        list.addLast("!");
        assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.set(4, "great"));
        list.set(3, ".");
        assertEquals(list.toArrayList(), Arrays.asList("structures", "is", "fun", "."));
        list.removeLast();
        assertEquals(list.size(), 3);
        list.removeLast();
        assertEquals(list.toArrayList(), Arrays.asList("structures", "is"));
        list.removeFirst();
        list.removeLast();
        assertTrue(list.isEmpty());
        assertEquals(list.size(), 0);

        list.addLast("linked");
        list.addLast("list");
        list.addFirst("is");
        assertEquals(list.size(), 3);
        list.insert(1, "a");
        assertEquals(list.toArrayList(), Arrays.asList("is", "a", "linked", "list"));
        list.insert(0, "this");
        assertEquals(list.get(0), "this");
        list.insert(5, "!");
        assertEquals(list.size(), 6);
        assertEquals(list.get(5), "!");
        list.forEach(System.out::println);
    }
}