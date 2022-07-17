# Linked List in Python
The complete code can be found at [linked_list.py](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/python/lists/linked_list.py).

## A few notes on implementation
When translating the pseudo code into its Python's implementation, we shall pay attention to comparison between objects.

```python
def remove_first(self):
    if self.is_empty():
        raise IndexError('Remove from empty linked list')
    else:
        self._head = self._head.next
        if self._head is None:
            self._tail = None
        self._size -= 1
```

The code above shows the procedure of removing the first element in a linked list. If there was only one element, then both `head` and `tail` should be set to `None`. To check whether current head is `None`, we use the built-in `is`, instead of `==`.

> `is` will return True if two variables point to the same object (in memory). `==` will return True if the objects referred to by the variables are equal. See more at [Is there a difference between "==" and "is"?](https://stackoverflow.com/questions/132988/).

### Shorter yet obscure
As for nodes in linked lists, we shall focus on how to assign values and relink the `next` pointer. Sometimes, *relinking* twice can be shortened by leveraging of `__init__`. For example, the following code is to add an element onto a stack (we will check it later):

```python
# push() = add_first()
def push(self, item):
    node = Node(item)
    node.next = self._head
    self._head = node
    self._size += 1
```

It can be shortened to:

```python
# push() = add_first()
def push(self, item):
    self._head = Node(item, self._head)
    self._size += 1
```

It is shorter, but a bit more difficult to understand. Some people may strive for elegance and prefer to shorter code, while others may give a priority to ease-of-understanding. Again, it is a *trade-off*.


## Stack based on linked lists
In this subsection, we are going to implement a stack based on singly linked lists. 

> A stack is a collection of objects that are inserted and removed according to the last-in, first-out (LIFO) policy.

The two main operations of a stack can be described in the following, and both of them run in \\(O(1)\\):

- `push()`: `add_first()` in a linked list
- `pop()`: `remove_first()` in a linked list

As we can see, only the `head` pointer is necessary here. The complete code can be found at [linked_stack.py](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/python/lists/linked_stack.py), which is a simplified implementation of a linked list.

|  | Array-based stack | LinkedList-based stack  | Note |
| ---- | ---- | ----- | ----- |
| `push()` | \\(O(1)\\) | \\(O(1)\\) | Array-based is amortized |
| `pop()` | \\(O(1)\\) | \\(O(1)\\) | Array-based is amortized |

## Queue based on linked lists
In this subsection, we are going to implement a stack based on singly linked lists. 

> A queue is a collection of objects that are inserted and removed according to the first-in, first-out (FIFO) policy.

The two main operations of a queue can be described in the following, and both of them run in \\(O(1)\\):

- `enqueue()`: `add_last()` in a linked list
- `dequeue()`: `remove_first()` in a linked list

We shall maintain both `head` and `tail` for a queue. The complete code can be found at [linked_queue.py](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/python/lists/linked_queue.py), which is also a simplified implementation of a linked list.

|  | Array-based queue | LinkedList-based queue  | Note |
| ---- | ---- | ----- | ----- |
| `enqueue()` | \\(O(1)\\) | \\(O(1)\\) | Array-based is amortized |
| `dequeue()` | \\(O(N)\\) | \\(O(1)\\) | Not the circular array |

## Circularly linked list
A **circularly linked list** is essentially a singly linked list in which the `next` reference to the tail node is set to refer back to the head of the list (rather than `null`).

<img src="image/circular_linkedlist.png" width="80%">

This ADT can be used in applications in which data can be more naturally viewed as having a *cyclic order*, with well-defined neighboring relationships, but no fixed beginning or end. Note that there is a link from `tail` to `head`, so only `tail` is required for a circularly linked list.

In what follows, We elaborate some key implementations of this ADT in Python, and the full code can be found at [circular_linked_list.py](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/python/lists/circular_linked_list.py):

### `addFirst()`

```python
def add_first(self, item):
    if self.is_empty():
        self._tail = self.Node(item)
        self._tail.next = self._tail
    else:
        self._tail.next = self.Node(item, self._tail.next)
    self._size += 1
```

### `addLast()`

```python
def add_last(self, item):
    self.add_first(item)
    self._tail = self._tail.next
```

### `removeFirst()`

```python
def remove_first(self):
    if self.is_empty():
        raise NoElement
    head = self._tail.next
    if head is self._tail:
        # only one element
        self._tail = None
    else:
        self._tail.next = head.next
    self._size -= 1
```

