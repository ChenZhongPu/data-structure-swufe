package org.swufe.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SequentialSearchTest {
    @Test
    void operation() {
        SequentialSearch<Integer, String> search = new SequentialSearch<>();
        assertTrue(search.isEmpty());
        search.put(1, "1");
        search.put(8, "8");
        search.put(9, "9");
        search.put(6, "6");
        assertEquals(search.get(8), "8");
        search.put(8, "eight");
        assertEquals(search.get(8), "eight");
        search.delete(8);
        assertEquals(search.size(), 3);
        assertFalse(search.contains(8));
    }
}