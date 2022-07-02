package org.swufe.datastructure;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTest {

    @Test
    void search() {
        BinarySearch binarySearch = new BinarySearch();
        List<Integer> list = Arrays.asList(1, 2, 4, 5, 6, 9, 10);
        assertEquals(binarySearch.search(list, 1), 0);
        assertEquals(binarySearch.search(list, 10), 6);
        assertEquals(binarySearch.search(list, 5), 3);
        assertEquals(binarySearch.search(list, 3), -1);
    }

    @Test
    void oneHit() {
        BinarySearch binarySearch = new BinarySearch();
        List<Integer> list = List.of(5);
        assertEquals(binarySearch.search(list, 5), 0);
    }

    @Test
    void oneMiss() {
        BinarySearch binarySearch = new BinarySearch();
        List<Integer> list = List.of(5);
        assertEquals(binarySearch.search(list, 4), -1);
    }

    @Test
    void empty() {
        BinarySearch binarySearch = new BinarySearch();
        List<Integer> list = Collections.emptyList();
        assertEquals(binarySearch.search(list, 1), -1);
    }
}