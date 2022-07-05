package org.swufe.datastructure;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CircularArrayQueueTest {
    @Test
    void operation() {
        CircularArrayQueue<String> q = new CircularArrayQueue<>();
        q.enqueue("data");
        q.enqueue("structures");
        q.enqueue("is");
        q.enqueue("fun");
        assertFalse(q.isEmpty());
        assertEquals(q.dequeue(), "data");
        assertEquals(q.size(), 3);
        assertEquals(q.dequeue(), "structures");
        assertEquals(q.dequeue(), "is");
        assertEquals(q.dequeue(), "fun");
        assertThrowsExactly(NoSuchElementException.class, q::dequeue);
        assertTrue(q.isEmpty());
        for (int i = 0; i < 20; i++) {
            q.enqueue(String.valueOf(i));
        }
        assertEquals(q.size(), 20);
    }
}