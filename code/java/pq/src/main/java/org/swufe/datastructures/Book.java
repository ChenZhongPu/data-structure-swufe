package org.swufe.datastructures;

import java.util.Objects;

public class Book implements Comparable<Book> {
    private String name;
    private double price;

    private String author;

    public Book(String name, double price, String author) {
        this.name = name;
        this.price = price;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getAuthor() {
        return author;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int compareTo(Book o) {
        return Double.compare(price, o.price);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, price, author);
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                '}';
    }
}
