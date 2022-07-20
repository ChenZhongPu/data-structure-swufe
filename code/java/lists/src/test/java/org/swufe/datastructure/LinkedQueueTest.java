package org.swufe.datastructure;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LinkedQueueTest {
    @Test
    void operation() {
        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("D");
        queue.enqueue("A");
        queue.enqueue("T");
        queue.enqueue("A");
        queue.forEach(System.out::println);
        assertFalse(queue.isEmpty());
        assertEquals(queue.peek(), "D");
        assertEquals(queue.dequeue(), "D");
        assertEquals(queue.size(), 3);
        assertEquals(queue.dequeue(), "A");
        assertEquals(queue.dequeue(), "T");
        assertEquals(queue.dequeue(), "A");
        assertThrows(NoSuchElementException.class, queue::dequeue);
        assertTrue(queue.isEmpty());
    }
}