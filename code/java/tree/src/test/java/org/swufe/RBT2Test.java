package org.swufe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RBT2Test {
    @Test
    void balanced() {
        RBT2<Integer> rbt = new RBT2<>();
        for (int i = 0; i < 1000; i +=2) {
            rbt.put(i);
        }
        assertTrue(rbt.height() < 20);
        assertTrue(rbt.isRBT());
        assertTrue(rbt.contains(666));
        assertFalse(rbt.contains(555));

        rbt.remove(444);
        assertFalse(rbt.contains(444));
        rbt.remove(1);
        rbt.remove(10);
        rbt.remove(12);
        rbt.remove(100);
        assertEquals(rbt.size(), 496);
        assertTrue(rbt.isRBT());
    }
}