# Queues in Java
An implementation of our `Queue` API based on the `ArrayList` data structure is also straightforward, and this is left as an exercise.

## Using an array circularly
The queues based on the `ArrayList` also suffers from the bad performance caused by `enqueue()`. Therefore, we will use an array circularly as the underlying data structure.

```java
public class CircularArrayQueue<Item> implements Iterable<Item> {
    private static final int DEFAULT_CAPACITY = 10;
    private Item[] a;
    private int n; // number of elements in queue
    private int front;
    ...
}
```

Once you have understood how the circular array works, you can translate the Python implementation to its Java counterpart with ease. For example,

```java
public void enqueue(Item item) {
    if (n == a.length) {
        resize(2 * a.length);
    }
    int avail = (front + n) % a.length;
    a[avail] = item;
    n += 1;
}
```

```java
public Item dequeue() {
    if (isEmpty()) {
        throw new NoSuchElementException("Dequeue from empty queue!");
    }
    if (n <= a.length / 4) {
        resize(a.length / 2);
    }
    Item answer = a[front];
    a[front] = null;
    n -= 1;
    front = (front + 1) % a.length;
    return answer;
}
```

Except for the syntax itself, they are the same exactly! The complete code can be found at [CircularQueue.java](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/java/stack-queue/src/main/java/org/swufe/datastructure/CircularQueue.java).

## An experimental evaluation
According to the theoretical analysis, the time complexity of `dequeue()` of circular queues is \\(O(1)\\). To what follows, I design a small benchmark to evaluate its performance ([Benchmark.java](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/java/stack-queue/src/main/java/org/swufe/datastructure/Benchmark.java)).

We initialize the size in the range of [10000000, 20000000, 30000000, 40000000, 50000000], and we find that no matter how the size changes, the measured is always 0! It is super fast with a constant time complexity.


## A few notes on `Queue` interface
> When you need queues, please have a look at the implementing classes of [Queue](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Queue.html) interface, instead of implementing a new one from the scratch.

Java offers the [Queue](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Queue.html) interface, which has the following methods:

- `add()`: Inserts the specified element into this queue if it is possible to do so immediately without violating capacity restrictions, returning true upon success and throwing an IllegalStateException if no space is currently available.
- `remove()`: Retrieves and removes the head of this queue.

These two methods would throw exceptions. In contrast, it also provides a pair of APIs to return special value:

- `offer()`: Inserts the specified element into this queue if it is possible to do so immediately without violating capacity restrictions.
- `poll()`: Retrieves and removes the head of this queue, or returns null if this queue is empty.

To use a queue, we often need to choose one of its implementing classes, such as [ArrayDeque](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayDeque.html), [LinkedList](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/LinkedList.html). For example,

```java
Queue<String> q = new LinkedList<>();
q.offer("data");
q.offer("structure");
q.offer("is");
q.offer("fun");
assert Objects.equals(q.poll(), "data");
assert Objects.equals(q.poll(), "structure");
```

Although we have not learned `LinkedList` in detail yet, we are still able to use its public APIs[^linkedlist] because the interface makes such promise.

---
[^linkedlist] [LinkedList](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/LinkedList.html) is very complex by offering plenty of APIs.