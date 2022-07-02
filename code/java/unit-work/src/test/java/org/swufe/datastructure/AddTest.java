package org.swufe.datastructure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddTest {

    @Test
    void add() {
        Add a = new Add();
        assertEquals(a.add(1, 2), 3);
    }

    @Test
    void addTwoNegatives() {
        Add a = new Add();
        assertEquals(a.add(-2, -2), -3);
    }
}