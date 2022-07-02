package org.swufe.datastructure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;

class NaiveTwoSumTest {

    @Test
    void twoSum() {
        NaiveTwoSum naiveTwoSum = new NaiveTwoSum();
        assertEquals(naiveTwoSum.twoSum(Arrays.asList(2, 7, 11, 15), 9),
                Arrays.asList(0, 1));
        assertEquals(naiveTwoSum.twoSum(Arrays.asList(3, 2, 4), 6),
                Arrays.asList(1, 2));
    }

    @Test
    void empty() {
        NaiveTwoSum naiveTwoSum = new NaiveTwoSum();
        assertEquals(naiveTwoSum.twoSum(Arrays.asList(3, 3), 7),
                Collections.emptyList());
    }
}