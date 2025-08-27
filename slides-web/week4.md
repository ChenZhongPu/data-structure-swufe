---
theme: seriph
title: Data Structures-Week4
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
download: true
---

# Data Structures

## Lecture 4: Queue and Deque

CHEN Zhongpu, Fall 2025

<div class="text-10px">
School of Computing and Artificial Intelligence, SWUFE
</div>
<div class="flex justify-center items-center h-60px mt-8px">
    <img src="/swufefull.svg" class="h-full" alt="swufe logo"/>
</div>

---

# A Small Quiz

1. What does it print?

```python
s = Stack()
s.push(1)
s.push(9)
s.push(8)
print(s.pop())
```

2. Suppose an initially empty stack `S` has executed a total of 25 `push` operations, 12 `top` operations, and 10 `pop` operations, 3 of which raised `Empty` errors that were caught and ignored. What is the current size of `S`? (R-6.2)

---

# Queue

> [Queue](https://dictionary.cambridge.org/dictionary/english/queue): A line of people, usually standing or in cars, waiting for something.

<div class="flex justify-center items-center h-220px mt-8px">
    <img src="/week4/atm.jpeg" class="h-full" alt="Parameters"/>
</div>

<v-click>

## A queue is a collection of objects that are inserted and removed according to the **first-in, first-out (FIFO)** principle

</v-click>

---

# 1. Queue ADT

- `enqueue(e)`: Add element `e` to the back of the queue.
- `dequeue()`: Remove and return the first element from the queue.

What is time complexity of the following implementations?

```python
class Queue:
    def __init__(self):
        self._data = []

    def enqueue(self, e):
        self._data.append(e)

    def dequeue(self):
        if self.is_empty():
            raise NoElement('Queue is empty')
        return self._data.pop(0)
```

What if we `enqueue` on the front while `dequeue` on the back? Please analyze the time complexity.

---

## Circular Queue

A better way is to use a circular queue, which is known as a **ring buffer**, or **circular buffer**.

We use two pointers to maintain the front and back of the queue.

- `front`: the index of the first element in the queue.
- `back`: the index where the next element will be inserted.

<div class="flex justify-center items-center h-260px mt-8px">
    <img src="/week4/circle_queue.png" class="h-full" alt="Parameters"/>
</div>

---

## Circular Queue

<div class="flex justify-center items-center h-260px mt-8px">
    <img src="/week4/circle_queue.png" class="h-full" alt="Circle Queue"/>
</div>

- What will happen if we `enqueue` or `dequeue` on the circular queue?
- What if `front` is `4`, `rear` is `9`, and we `enqueue` one more element?
- How to compute the size of the circular queue? (Note that, `capacity` is different from `size`)

```python
front = (front + 1) % capacity
rear = (rear + 1) % capacity
```

---

## Circular Queue

```python
class CircularQueue:
  def __init__(self):
    self._data = [None]*10
    self._size = 0
    self._front = 0
```

```python
def enqueue(self, item):
    """Time complexity: O(1)"""
    avail = (self._front + self._size) % len(self._data)
    self._data[avail] = item
    self._size += 1

def dequeue(self):
    """Time complexity: O(1)"""
    answer = self._data[self._front]
    self._data[self._front] = None
    self._front = (self._front + 1) % len(self._data)
    self._size -= 1
    return answer
```

---

## Circular Queue

What happens if `size` exceeds the `capacity` of the circular queue?

<div class="flex justify-center items-center h-260px mt-8px">
    <img src="/week4/resize.png" class="h-full" alt="Resize"/>
</div>

---

### Resize

```python
def _resize(self, capacity):
    assert capacity > self.size()
    old = self._data
    self._data = [None] * capacity
    walk = self._front
    for i in range(self._size):
        self._data[i] = old[walk]
        walk = (1 + walk) % len(old)
    self._front = 0
```

The complete code can be found at [circular_queue.py](https://github.com/ChenZhongPu/data-structure-swufe/blob/master/code/python/stack-queue/circular_queue.py).

---

# 2. Deque

**Double ended queue**, or `deque`. It is often pronounced "deck".

It supports insertion and deletion at both the front and the back of the queue in constant time.

- Insert to the front
- Insert to the back
- Remove from the front
- Remove from the back

<div class="flex justify-center items-center h-220px mt-8px">
    <img src="/week4/circle_queue.png" class="h-full" alt="Parameters"/>
</div>

---

## Deque in Python

Luckily, [collections.deque](https://docs.python.org/3/library/collections.html) has been designed in Python, which have fast appends and pops from both ends.

```python
from collections import deque
q = deque(['structures', 'is'])
q.append('fun') # add_last
q.append('!') # add_last
q.appendleft('data') # add_first
q.pop() # delete_last
print(q)
q.popleft() # delete_first
print(q)
```

Therefore, it can be used as a stack, queue, or deque.

---

## [Leetcode 933: Number of Recent Calls](https://leetcode.com/problems/number-of-recent-calls/)

You have a `RecentCounter` class which counts the number of recent requests within a certain time frame.

Implement the `RecentCounter` class:

- `RecentCounter()` Initializes the counter with zero recent requests.
- `int ping(int t)` Adds a new request at time `t`, where `t` represents some time in milliseconds, and returns the number of requests that has happened in the past 3000 milliseconds (including the new request). Specifically, return the number of requests that have happened in the inclusive range `[t - 3000, t]`.

It is guaranteed that every call to ping uses a strictly larger value of `t` than the previous call.

```python
class RecentCounter(object):

    def __init__(self):
      pass

    def ping(self, t):
      pass
```

---

```python
from collections import deque


class RecentCounter:
    def __init__(self):
        self.data = deque()

    def ping(self, t: int) -> int:
        while self.data and (t - 3000) > self.data[0]:
            self.data.popleft()
        self.data.append(t)
        return len(self.data)
```

Example:

```
RecentCounter recentCounter = new RecentCounter();
recentCounter.ping(1);     // requests = [1], range is [-2999,1], return 1
recentCounter.ping(100);   // requests = [1, 100], range is [-2900,100], return 2
recentCounter.ping(3001);  // requests = [1, 100, 3001], range is [1,3001], return 3
recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002], range is [2,3002], return 3
```

---

# 3. Exercise <arcticons-pentastic />

- C-6.22 Postfix notation
- [Data Structures in Postgraduate entrance examination](https://blog.csdn.net/qq_51636863/article/details/134141751)

---

# 4. Recursion

> Recursion is a method where the solution to a problem depends on solutions to smaller instances of the same problem.

Recursion (adjective: recursive) occurs when a thing is defined in terms of itself or of its type.

<v-click>

### Example: Fibonacci Sequence

$$F(0) = 0$$ $$F(1) = 1$$ $$F(n) = F(n-1) + F(n-2)$$

</v-click>

---

## Recursive Algorithm

- Base Step
- Recursive Step

```python
def fibonacci(n):
    if n == 0:
        return 0
    elif n == 1 or n == 2:
        return 1
    else:
        return fibonacci(n - 1) + fibonacci(n - 2)
```

A good recursive algorithm shouldn't have **overlapping sub-problems**.

<v-click>

### Turn Recursion into Iteration <arcticons-easy-coder />

Try to use for loop to implement the Fibonacci sequence.

</v-click>

---

## More Examples in Recursion <arcticons-abdirect />

- Given a list, please compute the sum of it.
- Given a list, please find the maximum element in it.
- R.4-7

<v-click>

### Search on a Sorted List <arcticons-pixel-search />

| 2   | 3   | 6   | 8   | 12  | 14  | 15  | 16  | 18  | 19  |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |

Compare the mid-item with the target element,

- If the mid-item is equal to the target element, return the index.
- If the mid-item is greater than the target element, search the left half.
- If the mid-item is less than the target element, search the right half.

</v-click>

---

### Binary Search （1）

```python
def search(a, target):
    high = len(a) - 1
    low = 0
    while high >= low:
        mid = low + (high - low) // 2
        if a[mid] == target:
            return mid
        elif a[mid] > target:
            high = mid - 1
        else:
            low = mid + 1
    return -1
```

What is the time complexity of the binary search?

---

### Binary Search （2）

```python
def binary_search(a, target):
    def search(low, high):
        if low > high:
            return -1
        mid = low + (high - low) // 2
        if a[mid] == target:
            return mid
        elif a[mid] > target:
            return search(low, mid - 1)
        else:
            return search(mid + 1, high)
    return search(0, len(a) - 1)

```

What is the time complexity of the binary search?

---

# Summary

- Queue, Circular Queue, Deque
- Recursion
