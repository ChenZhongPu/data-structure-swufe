# Case Study
In this section, we will study the *comparison* in depth and then implement a more flexible `MaxPQ`. Note that the basic skills can also be applied in all ordered collections, including the BST in the previous chapter. Finally, I will introduce the standard binary heap libraries.

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

#### A few notes on tuples
In many cases, items are organized as tuples. For example,

```python
books = [('Zoo', 30), ('Gone with the wind', 42), ('The A.B.C. Murders', 20)]
```

To sort these tuples in a list, a common method is to use `from operator import itemgetter`:

```python
# sort by its price (the second filed)
books.sort(key=itemgetter(1))

# sort by its name (the first filed)
books.sort(key=itemgetter(0))
```

## A flexible MaxPQ
### Java
The `MaxPQ` requires that the keys are ordered, but we should not always expect the class implements [Comparable](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Comparable.html) in Java. Instead, users can pass an extra parameter as the comparator.

- If this comparator is null, then we still try to use the `Comparable` interface as the last resort.
- Otherwise, we would use this self-defined comparator.

The complete code can be found at [MaxPQ2.java](https://github.com/ChenZhongPu/data-structure-swufe/blob/master/code/java/pq/src/main/java/org/swufe/datastructures/MaxPQ2.java).

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

## Standard libraries

> Again, like queues, we do not have to implement our own priority queues in most cases, and you should first check if the standard libraries can meet your requirements.

### Java
Among Java's collections family, [PriorityQueue](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/PriorityQueue.html) ,implementing interface [Queue](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Queue.html), works like [MaxPQ2.java](https://github.com/ChenZhongPu/data-structure-swufe/blob/master/code/java/pq/src/main/java/org/swufe/datastructures/MaxPQ2.java). The elements of the priority queue are ordered according to their *natural ordering* (i.e., `Comparable`), or by a `Comparator`. **Note that it is a minimum heap**.

- *insert*: `add()` or `offer()`
- *return the minimum*: `peek()`
- *remove the minimum*: `remove()` or `poll`

```java
Queue<Integer> pq = new PriorityQueue<>();
pq.add(1);
pq.add(9);
pq.add(4);
pq.add(0);
pq.add(6);
pq.add(3);
System.out.println(pq.peek()); // 0
System.out.println(pq.remove()); // 0
System.out.println(pq.peek()); // 1
```

### Python
Module [heapq](https://docs.python.org/3/library/heapq.html) provides an implementation of the heap queue algorithm, also known as the priority queue algorithm. Note that `heap[0]` is the smallest item, so **it is also a minimum heap**. 

```python
pq = []
heapq.heappush(pq, 1)
heapq.heappush(pq, 9)
heapq.heappush(pq, 4)
heapq.heappush(pq, 0)
heapq.heappush(pq, 6)
heapq.heappush(pq, 3)
print(heapq.heappop(pq))  # 0
print(pq[0])  # 1: works like `peek()`
print(heapq.heappop(pq))  # 1
```

By the way, the tutorial also provides an approach to solve the problem that the data elements are not comparable:

```python
from dataclasses import dataclass, field
from typing import Any

@dataclass(order=True)
class PrioritizedItem:
    priority: int
    item: Any=field(compare=False)
```


---
[^reverse1] If you would like a reverse order, you can use `Arrays.sort(a, Collections.reverseOrder())`. As for a list, you can use `Collections.sort(a, Collections.reverseOrder())`.