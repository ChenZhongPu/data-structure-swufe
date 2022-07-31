package org.swufe.datastructures;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class BookNameLenComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return Integer.compare(o1.getName().length(), o2.getName().length());
    }
}

class MaxPQ2Test {
    @Test
    void operation() {
        MaxPQ2<Integer> pq = new MaxPQ2<>();

        assertTrue(pq.isEmpty());
        pq.insert(1);
        pq.insert(9);
        pq.insert(4);
        pq.insert(9);
        pq.insert(6);
        pq.insert(11);
        pq.insert(3);
        pq.insert(7);

        assertEquals(pq.size(), 8);
        assertEquals(pq.max(), 11);
        assertEquals(pq.delMax(), 11);
        assertEquals(pq.max(), 9);
    }

    @Test
    void selfComparator() {
        MaxPQ2<Book> pq = new MaxPQ2<>(new BookNameLenComparator());
        pq.insert(new Book("ABC", 12, "Lu Xun"));
        pq.insert(new Book("A", 13, "Lu Xun"));
        pq.insert(new Book("ABCD", 30, "Lu Xun"));
        pq.insert(new Book("AAAA", 22, "Lu Xun"));
        pq.insert(new Book("HARD SOFT", 28, "Lu Xun"));
        pq.insert(new Book("Sun Moon and Me", 8, "Lu Xun"));
        pq.insert(new Book("Sea", 14, "Lu Xun"));
        pq.insert(new Book("Old man", 40, "Lu Xun"));

        assertEquals(pq.size(), 8);
        assertEquals(pq.max().getName(), "Sun Moon and Me");
        pq.delMax();
        assertEquals(pq.max().getName(), "HARD SOFT");
    }
}