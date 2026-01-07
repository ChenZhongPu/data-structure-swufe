---
theme: seriph
title: Data Structures-Week6
background: https://cover.sli.dev
info: |
  ## Slides for Data Structure, SWUFE 
  Source code can be found at [github](https://github.com/ChenZhongPu/data-structure-swufe)
author: CHEN Zhongpu
highlighter: shiki
drawings:
  persist: false
transition: slide-left
mdc: true
selectable: true
---

# Data Structures

## Lecture 6: Doubly Linked List

CHEN Zhongpu, Fall 2025

<div class="text-10px">
School of Computing and Artificial Intelligence, SWUFE
</div>
<div class="flex justify-center items-center h-60px mt-8px">
    <img src="/swufefull.svg" class="h-full" alt="swufe logo"/>
</div>

---

## Quiz

Please describe the main idea of `remoteLast()`, and how about the time complexity?

<div class="flex justify-center items-center h-120px mt-8px">
    <img src="/week6/removelast.png"  class="h-full" alt="removelast"/>
</div>

```python
def remove_last(self):
    if self.is_empty():
        raise IndexError('Remove from empty linked list')
    else:
        if self._head is self._tail:
            # only one element
            self._head = None
            self._tail = None
        else:
            walk = self._head
            while walk.next is not self._tail:
                walk = walk.next
            walk.next = None
            self._tail = walk
        self._size -= 1
```

---

## What If We Have a Backward Pointer? <arcticons-thinkfree />

<div class="flex justify-center items-center h-120px mt-8px">
    <img src="/week6/doubly.png"  class="h-full" alt="doublylinkedlist"/>
</div>

<v-click>

### Doubly Linked List <arcticons-doubletwistpro />

By default, a linked list is a `singly linked list`, whose node has only one link to the next node. If every node also has a link to its **previous** node, it is called `doubly linked list`.

How to define the node in a doubly linked list?

<v-click>

```python
class Node:
    def __init__(self, item=None, prev=None, next=None):
        self.item = item
        self.prev = prev
        self.next = next
```

</v-click>

</v-click>

---

# 1. Remove Last

What is the time complexity here? <arcticons-metamask />

<div class="flex justify-center items-center h-400px mt-8px">
    <img src="/week6/alg-removelast.png"  class="h-full" alt="removelast"/>
</div>

---

## Header, Trailer, and Sentinel <arcticons-clickworker />

In order to avoid such boundaries checking, it helps to add special nodes (a.k.a. `sentinel`) at both ends of the list:

- A header node at the beginning of the list
- A trailer node at the end of the list.

<div class="flex justify-center items-center h-120px mt-8px">
    <img src="/week6/sentinel.png"  class="h-full" alt="sentinel"/>
</div>

Note that they are never changed.

---

## Remove Last (2)

<div class="flex justify-center items-center h-340px mt-8px">
    <img src="/week6/alg-removelast2.png"  class="h-full" alt="sentinel"/>
</div>

Try to draw the doubly linked list and the pointers after removing the last element.

---

# 2. A General Remove Method

```python
def _remove(self, node):
    predecessor = node.prev
    successor = node.next
    predecessor.next = successor
    successor.prev = predecessor
    self._size -= 1
```

- Again, try to draw the doubly linked list and the pointers after removing the node.
- Given `_remove`, how to remove the first and the last element?

---

# 3. A General Add Method

```python
def _add_between(self, item, predecessor, successor):
    node = self.Node(item, predecessor, successor)
    predecessor.next = node
    successor.prev = node
    self._size += 1
```

- Again, try to draw the doubly linked list and the pointers after adding a node.
- Given `_add_between`, how to add a node at the beginning and the end?

<!-- <v-click>

## Hands-on Data Structures <arcticons-handynewsreader />

<https://zhongpu.info/data-structure-swufe/>

<div class="flex justify-center items-center h-140px mt-8px">
    <img src="/week6/handson.png"  class="h-full" alt="hands-on"/>
</div>

</v-click> -->

---

# 4. Exercises

> Suggestions: make drawings using the visual representation described in the text.

(1) Suppose that `x` is a linked list `Node`. What does the following code do?

```python
t.next = x.next
x.next = t
```

(2) Why does the following code fragment not do the same thing as in the previous question?

```python
x.next = t
t.next = x.next
```

---

# 4. Exercises

> Suggestions: make drawings using the visual representation described in the text.

(3) Write a method `max()` that takes a reference to the first node in a linked list as argument and returns the value of the maximum key in the list. Assume that all keys are positive integers, and return 0 if the list is empty.

(4) Develop a recursive solution to the previous question.

(5) Given a doubly linked list `L`, in which a node consists of `prev`, `item` and `next`. How to delete node `p` from `L`? ([Postgraduate entrance exam](https://blog.csdn.net/qq_51636863/article/details/134141751), 2016)

- A. p.next.prev = p.prev; p.prev.next = p.prev;
- B. p.next.prev = p.next; p.prev.next = p.next;
- C. p.next.prev = p.next; p.prev.next = p.prev;
- D. p.next.prev = p.prev; p.prev.next = p.next;

Read more at [18 linked list problems](http://cslibrary.stanford.edu/105/LinkedListProblems.pdf) and [LeetCode](https://leetcode.com/problem-list/linked-list/).

---

# 5. Iteration

> [Iteration](https://en.wikipedia.org/wiki/Iteration) is the repetition of a process in order to generate a sequence of outcomes.

If a data structure is _iterable_, it means that it can be iterated over, e.g., in a `for` loop.

```python
for s in "hello":
    print(s)
```

## [So, what makes a data structure _iterable_?](https://stackoverflow.com/questions/9884132/what-are-iterator-iterable-and-iteration)

It has a method `__iter__()` that returns an _iterator_ object. In other words, `for s in "hello"` is, in fact,
`for s in "hello".__iter__()`. An **iterator** is an object with a method `__next__()` that returns the next element in the sequence, if any, or raise a `StopIteration` exception to indicate that there are no further elements.

```python
numbers = [1, 2, 3]
num_iter = iter(numbers)
print(next(num_iter))  # 1
print(next(num_iter))  # 2
print(next(num_iter))  # 3
# print(next(num_iter))  # StopIteration
```

---

## To Make Stack Iterable

```python
class Stack:
    """A last-in, first-out (LIFO) data structure."""

    def __init__(self):
        self._data = []

    def push(self, item):
        self._data.append(item)

    def pop(self):
        if self.is_empty():
            raise Exception('Pop from empty stack!')
        return self._data.pop()

    def size(self):
        return len(self._data)

    def __iter__(self):
      return reversed(self._data)
```

And you can also implement your own iterator class. See more in [stack.py](https://github.com/ChenZhongPu/data-structure-swufe/blob/master/code/python/stack-queue/stack.py).

---

## To Make Linked List Iterable

```python
class LinkedList:
  def __init__(self):
    self._head = None
    self._size = 0

  def add_first(self, item):
    self._head = Node(item, self._head)
    self._size += 1

  def __iter__(self):
    return LinekdListIterator(self._head)

class LinkedListIterator:
  def __init__(self, head):
    self._current = head

  def __next__(self):
    if self._current is None:
      raise StopIteration
    item = self._current.item
    self._current = self._current.next
    return item
```

---

### Iterator vs. Iterable

Please spot the bugs.

```python
lst = LinkedList()
lst.add_first(1)
lst.add_first(2)
lst.add_first(3)

for i in lst:
    print(i)

ite = LinkedListIterator(lst._head)

for i in ite:
    print(i)
```

By convention, an iterator must return itself as an iterator.

```python
class LinkedListIterator:
  def __iter__(self):
    return self
```

---

### Generator

In Python, making use of `generator` is a more elegant and Pythonic way to provide an iterator.

```python
class LinkedList:
  def __init__(self):
    self._head = None
    self._size = 0

  def add_first(self, item):
    self._head = Node(item, self._head)
    self._size += 1

  def __iter__(self):
    walk = self._head
    while walk is not None:
      yield walk.item
      walk = walk.next
```

> A [generator](https://wiki.python.org/moin/Generators) in Python is a function that returns an iterator using the `yield` keyword.
