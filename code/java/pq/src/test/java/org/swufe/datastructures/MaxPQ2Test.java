package org.swufe.datastructures;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

    @Test
    void immutable() {
        MaxPQ2<Book> pq = new MaxPQ2<>();
        Book b1 = new Book("Gone with the wind", 89, "Margaret Mitchell");
        Book b2 = new Book("Data structures", 120, "Unknown");
        Book b3 = new Book("The old man and the sea", 36, "Ernest Hemingway");
        pq.insert(b1);
        pq.insert(b2);
        pq.insert(b3);

        assertEquals(pq.max().getName(), "Data structures");
        b2.setPrice(b2.getPrice() * 0.5);
        assertEquals(pq.max().getName(), "Data structures");
    }

    @Test
    void fromListSwim() {
        List<Integer> data = Arrays.asList(1, 9, 4, 6, 8, 10, 7);
        MaxPQ2<Integer> pq = MaxPQ2.fromListBySwim(data);
        assertEquals(pq.max(), 10);
    }

    @Test
    void fromListSink() {
        List<Integer> data = Arrays.asList(1, 9, 4, 6, 8, 10, 7);
        MaxPQ2<Integer> pq = MaxPQ2.fromListBySink(data);
        assertEquals(pq.max(), 10);
    }

}