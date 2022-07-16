# Linked List in Java
The complete code can be found at [LinkedList.java](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/java/lists/src/main/java/org/swufe/datastructure/LinkedList.java).


## A few notes on implementation
It is good practice to make the inner `Node` class being *private static*:

```java
private static class Node<Item> {
    Item item;
    Node<Item> next;
    Node(Item item) {
        this.item = item;
        next = null;
    }
}
```

See more at **Item 24: Favor static member classes over nonstatic** in [Effective Java](https://book.douban.com/subject/27047716/).

## Stack based on linked lists
In this subsection, we are going to implement a stack based on singly linked lists. 

> A stack is a collection of objects that are inserted and removed according to the last-in, first-out (LIFO) policy.

The two main operations of a stack can be described in the following, and both of them run in \\(O(1)\\):

- `push()`: `add_first()` in a linked list
- `pop()`: `remove_first()` in a linked list

As we can see, only the `head` pointer is necessary here. The complete code can be found at [LinkedStack.java](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/java/lists/src/main/java/org/swufe/datastructure/LinkedStack.java), which is a simplified implementation of a linked list.

|  | Array-based stack | LinkedList-based stack  | Note |
| ---- | ---- | ----- | ----- |
| `push()` | \\(O(1)\\) | \\(O(1)\\) | Array-based is amortized |
| `pop()` | \\(O(1)\\) | \\(O(1)\\) | Array-based is amortized |


Note that the implementation of `push()` can also be shortened, as we did in [Linked List in Python: Shorter yet obscure](./linkedlist_python.md#shorter-yet-obscure).

## Queue based on linked lists
In this subsection, we are going to implement a stack based on singly linked lists. 

> A queue is a collection of objects that are inserted and removed according to the first-in, first-out (FIFO) policy.

The two main operations of a queue can be described in the following, and both of them run in \\(O(1)\\):

- `enqueue()`: `add_last()` in a linked list
- `dequeue()`: `remove_first()` in a linked list

We shall maintain both `head` and `tail` for a queue. The complete code can be found at [LinkedQueue.java](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/java/lists/src/main/java/org/swufe/datastructure/LinkedQueue.java), which is a simplified implementation of a linked list.

|  | Array-based queue | LinkedList-based queue  | Note |
| ---- | ---- | ----- | ----- |
| `enqueue()` | \\(O(1)\\) | \\(O(1)\\) | Array-based is amortized |
| `dequeue()` | \\(O(N)\\) | \\(O(1)\\) | Not the circular array |

## Circularly linked list
A **circularly linked list** is essentially a singly linked list in which the `next` reference to the tail node is set to refer back to the head of the list (rather than `null`).

<img src="image/circular_linkedlist.png" width="80%">

This ADT can be used in applications in which data can be more naturally viewed as having a *cyclic order*, with well-defined neighboring relationships, but no fixed beginning or end. Note that there is a link from `tail` to `head`, so only `tail` is required for a circularly linked list.

In what follows, We elaborate some key implementations of this ADT in Java, and the full code can be found at [CircularLinkedList.java](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/java/lists/src/main/java/org/swufe/datastructure/CircularLinkedList.java):
### `addFirst()`

```java
public void addFirst(Item item) {
    if (size == 0) {
        tail = new Node<>(item);
        tail.next = tail; // link to itself circularly
    } else {
        tail.next = new Node<>(item, tail.next);
    }
    size += 1;
}
```

### `addLast()`

```java
public void addLast(Item item) {
    addFirst(item);
    tail = tail.next;
}
```

### `removeFirst()`

```java
public void removeFirst() {
    if (size == 0) {
        throw new NoSuchElementException();
    }
    Node<Item> head = tail.next;
    if (head == tail) {
        // only one element
        tail = null;
    } else {
        tail.next = head.next;
    }
    size -= 1;
}
```

