# Java Built-in Data Structures
In this section, we will introduce three essential data structures in Java, and they are also referred as *collections*. A complete tutorial can be found [here](https://docs.oracle.com/javase/tutorial/collections/).

> A collection — sometimes called a container — is simply an object that groups multiple elements into a single unit. Collections are used to store, retrieve, manipulate, and communicate aggregate data.

Collections (e.g., `ArrayList` mentioned in the last section) are indispensable ingredients in our daily programming work, and they represent data items that form a natural group.

## Array
An array is a low-level data structure in Java. Generally, **if you don't have adequate reasons to choose an *array*, then a *list* is always preferred**. But sometimes a solid understanding about arrays is necessary if you want to design you own data structures. See more at [Arrays](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html).

The method that we use to refer to individual values in an array is numbering and then *indexing* them. If we have N values, we think of them as being numbered from 0 to N-1.

### Creating and initializing an array
Making an array in a Java program involves three distinct steps:

- Declare the array name and type.
- Create the array
- Initialize the array

The long form is to specify those three steps explicitly:

```java
double[] a; // declare
a = new double[N]; // create
for (int i = 0; i < N; i++) {
    a[i] = 3.14; // initialize
}
```

And the declaration and creation can be written in a short form:

```java
double[] a = new double[N];
```

Furthermore, those three steps can be even shortened into one line:

```java
double[] a = { 3.14, 3.15, 3.16 };
```

Note that like most programming languages, Java would provide default values even if you do not initialize them, but it is a bad practice to rely on the complier. So **please make an initialization explicitly before using an array**.

### Accessing an array
We can access an array via *indexing*:

```java
double d = a[0]; // get the first item
a[0] = 9.9; // update the first item
```

You should pay attention to the indexing range, because it will raise an `OutOfBoundsException` if you use the index less than 0 or greater than N - 1.

### Generics and arrays
Generics have been a part of the language since Java 5, and see more at [Generics](https://docs.oracle.com/javase/tutorial/java/generics/index.html). Generics make our code more flexible because they allow the data structure to hold different data types. In other words, it is possible to manage both *books* and *fruits* using one piece of code. That is cool!

But keep in mind that arrays and generics do not mix well. For example, the following code will result in *generic array creation* at compile time:

```java
T[] elements = new T[N];
```

Here I propose one solution to this problem. To manage different types of items, the `Grocery` class accepts generics types:

```java
public class Grocery<E> {
    private E[] elements;
    private int size = 0;
    private static final int INIT_CAPACITY = 16;

    public Grocery() {
        elements = (E[]) 
    }
}
```