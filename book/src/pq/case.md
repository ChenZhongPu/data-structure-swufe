# Case Study
In this section, we will study the *comparison* in depth and then implement a more flexible `MaxPQ`. Note that the basic skills can also be applied in all ordered collections, including the BST in the previous chapter.

## Comparison in depth
### Java
Given an array `int[] a = {1, 9, 4, 6, 3}`, how to sort it?

```java
Arrays.sort(a);
// a becomes {1, 3, 4, 6, 9}
```

It works because `int` has a built-in ascending order[^reverse1]. Similarly, given a list, you can use `Collections.sort(a)`. But how can you compare two books?

```java
public class Book {
    private String name;
    private double price;
    private String author;
    ...
}
```

One possible way is to let `Book` implement [Comparable](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Comparable.html), and override its `toCompare()` method. For example, the following code will sort books by their prices in ascending order:

```java
public class Book implements Comparable<Book> {
    @Override
    public int compareTo(Book o) {
        return Double.compare(price, o.price);
    }
}
```

Then you can sort books:

```java
List<Book> books = new ArrayList<>();
books.add(new Book("Gone with the wind", 89));
books.add(new Book("Data structures", 120));
books.add(new Book("The old man and the sea", 36));
Collections.sort(books);
```

However, **sometimes you are unable to change the class you want to compare, and you may even want different comparing rules in different settings**. In this case, you can use [Comparator](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Comparator.html) by providing an on-the-fly comparing criterion in its `compare()` method:

```java
class BookNameComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
```

Then, you can use the following code:

```java
// you can use either line, but the second one is recommended
Collections.sort(books, new BookNameComparator());
books.sort(new BookNameComparator());
```

In Java 8 and above, you also can use lambda expression to shorten the code without providing an explicit comparator class:

```java
// you can use either line, but the second one is recommended 
books.sort((a, b) -> a.getAuthor().compareTo(b.getAuthor()));
books.sort(Comparator.comparing(Book::getAuthor));
```

### Python
Python provides two kinds of basic methods to sort items. In what follows, I only take `sort()` method as the example, and most rules are also applicable for `sorted()`. 

- `sort()`: in-place sorting, as we have seen in Java
- `sorted()`: return a sorted collection, and leave the origin collection unchanged

```python
a = [1, 9, 4, 6, 3]
b = sorted(a)
# b is [1, 3, 4, 6, 9]
# a is unchanged
a.sort()
# now a becomes [1, 3, 4, 6, 9] 
```

In fact, the syntax of `sort()` is `list.sort(key=..., reverse=...)`. So we further specify two parameters:

- *key*: function that serves as a key for the sort comparison
- *reverse*: if `True`, the sorted list is reversed (or sorted in descending order)

```python
a = [1, 9, 4, 6, 3]
a.sort(reverse=True)
# a becomes [9, 6, 4, 3, 1] 
```

The role of `key` is similar to that of `Comparator` in Java. Suppose there is a `Book` class:

```python
class Book:
    def __init__(self, name, price, author):
        self.name = name
        self.price = price
        self.author = author
```

To pass a lambda expression to key:

```python
books.sort(key=lambda book: book.price)
```

You can also pass a built-in or self-defined method:

```python
def book_title(b):
    return b.name

books.sort(key=book_title)
```

Alternatively, you can also provide a method accepting two parameters, and returning -1, 1, 0 as the results. The mechanism is the same with `Comparator`: 

```python
from functools import cmp_to_key

def book_title_len(b1: Book, b2: Book):
    """the longer first"""
    if len(b1.name) < len(b2.name):
        return 1
    elif len(b1.name) > len(b2.name):
        return -1
    else:
        return 0

books.sort(key=cmp_to_key(book_title_len))
```

By default, the `sort()` method relies on `<` operator, and it can be overridden by the `__lt__()` method:

```python
def __lt__(self, other):
    return self.price < other.price
```

Then you can use `books.sort()` directly.

## A flexible MaxPQ
### Java
The `MaxPQ` requires that the keys are ordered, but we should not always expect the class implements [Comparable](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Comparable.html) in Java. Instead, users can pass an extra parameter as the comparator.

- If this comparator is null, then we still try to use the `Comparable` interface as the last resort.
- Otherwise, we would use this self-defined comparator.

The complete code can be found at [MaxPQ2.java]((https://github.com/ChenZhongPu/data-structure-swufe/blob/master/code/java/pq/src/main/java/org/swufe/datastructures/MaxPQ2.java).

### Python
Because Python is a dynamic language, the statement "*sometimes you are unable to change the class you want to compare*" does not hold true. For example, we can specify `__lt__()` in the runtime:

```python
class User:
    def __init__(self, name, age):
        self.name = name
        self.age = age

def user_cmp(self, other):
    return self.age < other.age

User.__lt__ = user_cmp
```

But this approach is not Pythonic. Alternatively, you can pass a function as a key for the sort comparison, as we did for `sort()` method. Another feasible choice is to pass a `less()` function that returns a boolean value directly. Such function can be seen as a user-defined comparator.

---
[^reverse1] If you would like a reverse order, you can use `Arrays.sort(a, Collections.reverseOrder())`. As for a list, you can use `Collections.sort(a, Collections.reverseOrder())`.