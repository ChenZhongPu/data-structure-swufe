# Java Built-in Data Structures
In this section, we will introduce arrays and three essential data structures (often referred as *collections*) in Java. A complete tutorial can be found [here](https://docs.oracle.com/javase/tutorial/collections/).

> A collection — sometimes called a container — is simply an object that groups multiple elements into a single unit. Collections are used to store, retrieve, manipulate, and communicate aggregate data.

Collections (e.g., `ArrayList` mentioned in the last section) are indispensable ingredients in our daily programming work, and they represent data items that form a natural group.

## Array
An array is a low-level data structure in Java, instead of a kind of collection. Generally, **if you don't have adequate reasons to choose an *array*, then a *list* is always preferred**. But sometimes a solid understanding about arrays is necessary if you want to design you own data structures. See more at [Arrays](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html).

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
a[0] = 42.0; // update the first item
```

You should pay attention to the indexing range, because it will raise an `OutOfBoundsException` if you use the index less than 0 or greater than N - 1.

### Iterating an array
You can use the tradition `for` loop to iterate an array:

```java
for (int i = 0; i < N; i++) {
    System.out.println(a[i]);
}
```

But for modern Java, you should prefer to the enhanced `for-each`, since it is likely to make the *OutOfBoundsException* using the traditional method:

```java
for (double d : a) {
    System.out.println(d);
}
```

### Generics and arrays

Suppose you have developed a `Library` class which manages books:

```java
public class Library {
    private Book[] books;
    
    public void add(Book book) {
        ...
    }
    ...
}
```

Then how can you reuse this class to manage other types, let's say `Fruit` for a grocery? A naive method is to repeat the code:

```java
public class Grocery {
    private Fruit[] fruits;
    
    public void add(Fruit fruit) {
        ...
    }
    ...
}
```

There is a golden rule in programming: **don't repeat yourself**, so we would like to manages either `Book` or `Fruit` using one piece of code. Since every class in Java is inherited from `Object`, what about `Object[]`? Well, it is feasible but it is also error-prone, because it cannot prevent users adding both books and fruits to our system at the same time. To solve this problem, you should use generics. Generics have been a part of the language since Java 5, and see more at [Generics](https://docs.oracle.com/javase/tutorial/java/generics/index.html).

But keep in mind that arrays and generics do not mix well. For example, the following code will result in *generic array creation* at compile time:

```java
T[] elements = new T[N];
```

Here I propose one solution to this problem. To manage different types of items, the `Manager` class accepts generics types (The complete code can be found at [Manager.java](https://github.com/ChenZhongPu/data-structure-swufe/code/java/start/src/Manager.java)):

```java
public class Manager<E> {
    private E[] elements;
    private int size = 0;
    private static final int INIT_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public Manager() {
        elements = (E[]) new Object[INIT_CAPACITY];
    }

    public void add(E item) {
        ...
    }
    ...
}
```

Since casting an `Object[]` to `E[]` is unsafe, we use `@SuppressWarnings("unchecked")`[^unchecked] to tell the complier that I know what I am doing and please don't give me any warning. Don't get frustrated if you don't fully understand what the code above means, and we will go back to discuss the code in detail later. What you need to know now is to realize the importance of generics and possible traps when combining generics and arrays.

By the way, it is very straightforward to make use of the generic class:

```java
Manager<Book> library = new Manager<>();
library.add(new Book("Gone with the wind", "Margaret Mitchell"));
library.display();
```

### Common operations of arrays
As a Java programmer, you should get familiar with `java.util.Arrays` which offering plenty of handy methods. You can refer to [JDK API Doc](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Arrays.html), and I will introduce some most commonly used methods in the following. Note that all methods are *static*.

- `asList​(T... a)`: Returns a fixed-size list backed by the specified array[^aslist].

```java
List<String> books = Arrays.asList("Gone with the Wind", "Hands on Data Structures");
```

- `fill​(int[] a, int val)`: Assigns the specified int value to each element of the specified array of ints.

```java
int[] arr = new int[100];
Arrays.fill(arr, 42);
```

- `copyOf​(int[] original, int newLength)`: Copies the specified array, truncating or padding with zeros (if necessary) so the copy has the specified length.

```java
int[] arr = {1, 2, 3};
// expand its size to 6
arr = Arrays.copyOf(arr, 6);
arr[3] = 4;
```

- `sort(int[] a)`: Sorts the specified array into ascending numerical order.

```java
int[] arr = {1, 9, 4, 2};
Arrays.sort(arr);
// now it becomes {1, 2, 4, 9}
```

## List
Here I mainly talk about [java.util.ArrayList](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayList.html), which is an resizable-array implementation of `java.util.List`. Keep in mind that never use raw types for collections, including `List`, `Set` and `Map`, and make friends with generics! 

```java
List<String> books = new ArrayList<>(); // generics
List fruits = new ArrayList(); //  don't do this!
```

Basically, you can regard `List` as a more powerful array, and you are required to know and practice at least those methods:

- `add()`
- `addAll()`
- `clear()`
- `contains()`
- `get()`
- `indexOf()`
- `iterator()`
- `remove()`
- `size()`
- `toArray()`

And of course, prefer to the enhanced `for-each` if you need to make an iteration over a list. Some legacy code may use `iterator()` method, but it is no longer preferred today. This suggestion is also valid for `Map` and `List`.

### Collections
`List` is a sub-interface of `Collection`. Like `java.util.Arrays`, [java.util.Collections](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Collections.html) provides plenty static handy methods. For example,

- `sort​(List<T> list)`: Sorts the specified list into ascending order, according to the natural ordering of its elements.

```java
List<Integer> list = Arrays.asList(1, 9, 4, 2);
Collections.sort(list);
// now list is [1, 2, 4, 9]
```

- `reverse​(List<?> list)`: Reverses the order of the elements in the specified list.

```java
List<Integer> list = Arrays.asList(1, 9, 4, 2);
Collections.reverse(list);
// now list is [2, 4, 9, 1]
```

- `max​(Collection<? extends T> coll)`: Returns the maximum element of the given collection, according to the natural ordering of its elements.

```java
List<Integer> list = Arrays.asList(1, 9, 4, 2);
int v = Collections.max(list); // v is 9
```

## Map
Here I mainly talk about [java.util.HashMap](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/HashMap.html). `Map` can be used to represent a mapping between a key and a value, and we assume that keys are unique. For example:

| Key | Value |
|---------|---------|
| Name     | Age     |
| ISBN | Price |
| City | Country |
| Country | GDP |

Different from `List`, a map should manage a collection of pairs. Let's consider a shopping cart which maintains pairs of ISBN (key) and amount (value).

```java
Map<String, Integer> cart = new HashMap<>();
```

Here we should specify the types of key and value as generics, respectively. To some extent, we can imagine a list as a map whose key is implicitly the index.

To add an item (three books whose ISBN is *7801*) into this cart:

```java
cart.put("7801", 3);
```

To get an item by its key (i.e, `ISBN`):

```java
int amount = cart.get("7801");
```

Then what if the key does not exist? This leaves as an exercise for readers. Now let's make an iteration over a map. 

```java
for (Map.Entry<String, Integer> entry : cart.entrySet()) {
    System.out.println(entry.getKey() + " : " + entry.getValue());
}
```

You are required to know and practice at least those methods:

- `clear()`
- `containsKey()`
- `entrySet()`
- `get()`
- `isEmpty()`
- `keySet()`
- `put()`
- `remove()`
- `size()`
- `values()`

## Set
Here I mainly talk about [java.util.HashSet](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/HashSet.html). Unlike a `list`, items in a `set` are unique.

```java
Set<String> set = new HashSet<>();
set.add("hello");
set.add("world");
System.out.println(set.size()); // 2
set.add("hello");
System.out.println(set.size()); // 2
```

Duplicate items are not allowed in a set, so the last statement will still print 2. Again, let's make an iteration:

```java
for (String s : set) {
   System.out.println(s);
}
```

Another key fact is that there is no order defined in a set, which is essentially `set` used in mathematics. For example, `(0, 1, 2)` and `(1, 0, 2)` are equivalent. 

```java
Set<Integer> a = new HashSet<>(Arrays.asList(0, 1, 2));
Set<Integer> b = new HashSet<>(Arrays.asList(1, 0, 2));
System.out.println(a.equals(b)); // true
```

In addition, we can also conclude that indexing in a set is meaningless.

You are required to know and practice at least those methods:

- `add()`
- `clear()`
- `contains()`
- `isEmpty()`
- `remove()`
- `size()`

---
[^unchecked] This line is optional, and it is fine to remove it.

[^aslist] Unlike regular lists, the one returned by `asList()` is a fixed-size one, so we cannot add/remove items on it.