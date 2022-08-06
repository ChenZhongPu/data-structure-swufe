package org.swufe.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeparateChainingHashTest {
    @Test
    void operation() {
        SeparateChainingHash<Integer, String> st = new SeparateChainingHash<>();
        st.put(1, "1");
        st.put(9, "9");
        st.put(4, "4");
        st.put(6, "6");
        st.put(10, "10");
        assertFalse(st.isEmpty());
        assertEquals(st.get(6), "6");
        st.put(6, "six");
        assertEquals(st.get(6), "six");
        st.delete(6);
        assertFalse(st.contains(6));
        assertEquals(st.size(), 4);
    }
}