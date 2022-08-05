package org.swufe.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinearProbeHashTest {
    @Test
    void operation() {
        LinearProbeHash<Integer, String> probeHash = new LinearProbeHash<>();
        probeHash.put(1, "1");
        probeHash.put(2, "2");
        probeHash.put(16, "16");
        probeHash.put(6, "6");
        probeHash.put(7, "7");
        assertEquals(probeHash.size(), 5);
        assertEquals(probeHash.get(16), "16");
        probeHash.put(16, "sixteen");
        assertEquals(probeHash.get(16), "sixteen");
        probeHash.delete(16);
        assertFalse(probeHash.contains(16));
        assertEquals(probeHash.size(), 4);
    }
}