package org.swufe.datastructure;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LinkedStackTest {
    @Test
    void operation() {
        LinkedStack<Integer> stack = new LinkedStack<>();
        assertEquals(stack.size(), 0);
        stack.push(5);
        assertFalse(stack.isEmpty());
        assertEquals(stack.size(), 1);
        stack.push(3);
        assertEquals(stack.peek(), 3);
        assertEquals(stack.pop(), 3);
        assertEquals(stack.pop(), 5);
        assertThrowsExactly(NoSuchElementException.class, stack::pop);
        assertEquals(stack.size(), 0);
        assertTrue(stack.isEmpty());
    }
}