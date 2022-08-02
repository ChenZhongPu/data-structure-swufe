package org.swufe.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxPQTest {
    @Test
    void operation() {
        MaxPQ<Integer> pq = new MaxPQ<>();
        assertTrue(pq.isEmpty());
        pq.insert(1);
        pq.insert(9);
        pq.insert(4);
        pq.insert(9);
        pq.insert(6);
        pq.insert(11);
        pq.insert(3);
        pq.insert(7);

        assertEquals(pq.size(), 8);
        assertEquals(pq.max(), 11);
        assertEquals(pq.delMax(), 11);
        assertEquals(pq.max(), 9);
    }

    @Test
    void fromArray() {
        Integer[] data = {1, 9, 4, 6, 8, 10, 7};
        MaxPQ<Integer> pq = new MaxPQ<>(data);
        assertEquals(pq.max(), 10);
    }
}