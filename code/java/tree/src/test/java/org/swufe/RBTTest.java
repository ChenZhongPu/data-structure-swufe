package org.swufe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RBTTest {
    @Test
    void balanced() {
        RBT<Integer> rbt = new RBT<>();
        for (int i = 0; i < 1000; i +=2) {
            rbt.put(i);
        }
        assertTrue(rbt.height() < 20);
        assertTrue(rbt.contains(666));
        assertFalse(rbt.contains(555));
    }
}