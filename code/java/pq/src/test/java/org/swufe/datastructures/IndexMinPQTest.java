package org.swufe.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndexMinPQTest {
    @Test
    void operation() {
        IndexMinPQ<String> minPQ = new IndexMinPQ<>(10);
        minPQ.insert(1, "Math");
        minPQ.insert(2, "Data");
        minPQ.insert(3, "English");
        minPQ.insert(5, "Biology");
        minPQ.insert(6, "History");

        assertTrue(minPQ.contains(1));
        assertFalse(minPQ.contains(4));
        assertEquals(minPQ.minIndex(), 5);
        assertEquals(minPQ.minKey(), "Biology");
        assertEquals(minPQ.keyOf(3), "English");

        minPQ.delMin();
        assertEquals(minPQ.minIndex(), 2);
        assertEquals(minPQ.minKey(), "Data");
        assertEquals(minPQ.size(), 4);

        minPQ.changeKey(1, "Child Math");
        assertEquals(minPQ.minIndex(), 1);
        assertEquals(minPQ.minKey(), "Child Math");
    }
}