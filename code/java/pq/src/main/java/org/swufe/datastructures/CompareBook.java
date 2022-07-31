package org.swufe.datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class BookNameComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

public class CompareBook {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Gone with the wind", 89, "Margaret Mitchell"));
        books.add(new Book("Data structures", 120, "Unknown"));
        books.add(new Book("The old man and the sea", 36, "Ernest Hemingway"));
        Collections.sort(books);
        books.forEach(System.out::println);
        System.out.println("----");

        // you can use either one
        Collections.sort(books, new BookNameComparator());
        books.forEach(System.out::println);
        System.out.println("----");
        books.sort(new BookNameComparator());
        books.forEach(System.out::println);
        System.out.println("----");

        // you can use either one
        books.sort((a, b) -> a.getAuthor().compareTo(b.getAuthor()));
        books.forEach(System.out::println);
        System.out.println("----");
        books.sort(Comparator.comparing(Book::getAuthor));
        books.forEach(System.out::println);
        System.out.println("----");
    }
}
