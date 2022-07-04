package org.swufe.datastructure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListStackTest {
    @Test
    void operation() {
        ArrayListStack<Integer> stack = new ArrayListStack<>();
        assertEquals(stack.size(), 0);
        stack.push(5);
        assertFalse(stack.isEmpty());
        assertEquals(stack.size(), 1);
        stack.push(3);
        assertEquals(stack.pop(), 3);
        assertEquals(stack.pop(), 5);
        assertNull(stack.pop());
        assertEquals(stack.size(), 0);
        assertTrue(stack.isEmpty());
    }
}