# Stack In Java
> Java has a built-in library called `java.util.Stack`, but you should avoid using it when you want a stack. It has several additional operations that are not normally associated with a stack, e.g., getting the ith element. 

## Stack based on ArrayList
Our first stacks are based on `ArrayList`, and the overall implementation is very similar to the one based on the `list` in Python. An upfront note is that generics are preferred for collections in Java, so the class is declared like:

```java
public class ArrayListStack<Item> {
    private List<Item> elments;

    public ArrayListStack() {
        elments = new ArrayList<>();
    }
    ...
}
```

Some students may still get confused about the motivation of adopting the `generics`. It is very good feature in static languages (e.g., Java), because it will enforce types checking in the compiling stage. To put it in another way, it can forbid us mixing *apples* with *oranges*. In contrast, Python lacks such a useful feature, so the error-prone code is valid in Python:

```python
s = Stack()
s.push(1)
s.push('apple')
```

Now let's go back to the Java's implementation. As we can see, `ArrayListStack` wraps an array list inside, and the implementations of these APIs are generally straightforward as long as you know the basic usage of an `ArrayList`.

- `push()` is based on `add()`:

```java
public void push(Item item) {
    elements.add(item);
}
```

- `size()` and `isEmpty()` have the methods with the same name:

```java
public int size() {
    return elements.size();
}

public boolean isEmpty() {
    return elements.isEmpty();
}
```

- `pop()` is based on `remove()`, and it will return `null` if the stack is empty:

```java
public Item pop() {
    if (isEmpty()) {
        return null;
    }
    Item v = elements.get(size() - 1);
    elements.remove(size() - 1);
    return v;
}
```

Another common approach is to throw an exception if the stack is empty, and we adopt this implementation in this book:

```java
if (isEmpty()) {
    throw new NoSuchElementException("Pop from empty stack!");
}
```

## Iterator
Iterators play the magic behind the scenes when we use the enhanced *for* loop. 

In this subsection, we are going to make the following code valid:

```java
ArrayListStack<String> stack = new ArrayListStack<>();
...
for (String s : stack) {
    System.out.println(s);
}
```

To make a class *iterable*, the first step is to add the phrase implements [Iterable](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Iterable.html) to its declaration, matching the interface

```java
public interface Iterable<Item> {
    Iterator<Item> iterator();
}
```

So, the signature of the class becomes

```java
public class ArrayListStack<Item> implements Iterable<Item>
```

It is often a good practice to prepare a single class to implement [Iterator](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Iterator.html), where at least `next()` and `hasNext()` should be implemented[^next]. Also, this concrete iterator is designed as an *inner* class, so it can access its parent class directly[^inner].

```java
private class ReverseArrayListIterator implements Iterator<Item> {
    private int i = elements.size();
    @Override
    public boolean hasNext() {
        return i > 0;
    }

    @Override
    public Item next() {
        return elements.get(--i);
    }
}
```


The complete code can be found at [ArrayListStack.java](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/java/stack-queue/src/main/java/org/swufe/datastructure/ArrayListStack.java).

By the way, [Iterable](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Iterable.html) has offered a default `forEach()` API since 1.8, so the print code can be shorten to 

```java
stack.forEach(System.out::println);
```

## Application: matching parentheses & arithmetic expression evaluation

Readers can try to implement the client code using `ArrayListStack` for these two applications, and this is left as an exercise.

## Stack based on array
In [Stack in Python](./stack_python.md), we summarized the times complexity of the operations of a stack:

| Operation | Running time |
| -------- |  ------- |
| push()  | \\(O(1)\\) |
| pop() | \\(O(1)\\) |
| is_empty() | \\(O(1)\\) |
| size() | \\(O(1)\\) |

We also pointed out that as for `push()` and `pop()`, the worst time is is *amortized* because it may have a bad time cost once a while. Now let's explore what happens exactly.

First, both `ArrayList` in Java and `list` in Python are based on arrays whose size are fixed. So how can we `push()` a new item onto it when it is *full*? The solution is to resize the array and copy the items to a new array[^resize]. To understand this, we shall introduce two different concepts:

- **size**: how many items are there in the collection?
- **capacity**: how many items can this collection hold at most without resizing?

```java
List<String> list = new ArrayList<>();
```

The *size* of `list` is 0, and the default *capacity* is 10. So, even if you do not add any item into this list, it has an array with size of 10 inside. And after adding 10 items, the underlying array will be resized to a larger one in order to hold more data. During the resizing, a copying operation is required, so it can result in a bad running time once a while.

Therefore, if we would like to use arrays to hold the data for stacks, we shall deal with the resizing manually. The complete code can be found at [ArrayStack.java](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/java/stack-queue/src/main/java/org/swufe/datastructure/ArrayStack.java).


```java
private void resize(int capacity) {
    assert capacity >= n;
    a = Arrays.copyOf(a, capacity);
}
```

---
[^next] In Python, the `__next__()` can be regarded as the combination of `next()` and `hasNext()` here.

[^inner] Python's inner class does support this feature.

[^resize] The resizing operation also happens when the size is much smaller than the capacity.