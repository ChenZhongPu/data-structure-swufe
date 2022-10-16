package org.swufe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AVLTest {
    @Test
    void operation() {
        AVL<Integer> avl = new AVL<>();
        for (int i = 0; i < 1000; i += 2) {
            avl.put(i);
        }
        assertTrue(avl.getHeight() < 20);
        assertTrue(avl.contains(666));
        assertFalse(avl.contains(555));
        avl.remove(444);
        assertFalse(avl.contains(444));
        for (int i = 0; i < 1000; i += 50) {
            avl.remove(i);
        }
        assertTrue(avl.getHeight() < 20);
    }
}