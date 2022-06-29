import java.util.Arrays;

class Book {
    private String name;
    private String author;

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

class Fruit {
    private String name;

    public Fruit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                '}';
    }
}

public class Manager<E> {
    private E[] elements;
    private int size = 0;
    private static final int INIT_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public Manager() {
        elements = (E[]) new Object[INIT_CAPACITY];
    }

    public int getSize() {
        return size;
    }

    public void add(E item) {
        ensureCapacity();
        elements[size++] = item;
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.println(elements[i]);
        }
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    public static void main(String[] args) {
        Manager<Book> library = new Manager<>();
        library.add(new Book("Gone with the wind", "Margaret Mitchell"));
        library.display();
        System.out.println("--------");
        Manager<Fruit> grocery = new Manager<>();
        grocery.add(new Fruit("Apple"));
        grocery.add(new Fruit("Orange"));
        grocery.display();
    }
}