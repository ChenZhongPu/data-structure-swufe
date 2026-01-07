---
theme: seriph
title: Data Structures-Week3
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

## Lecture 3: Stack

CHEN Zhongpu, Fall 2025

<div class="text-10px">
School of Computing and Artificial Intelligence, SWUFE
</div>
<div class="flex justify-center items-center h-60px mt-8px">
    <img src="/swufefull.svg" class="h-full" alt="swufe logo"/>
</div>

---

# A Small Quiz

What is the time complexity of the following code in the worst case?

```python {*}{lines:true}
def find_max(a):
  m = a[0]
  for i in a:
    if i > m:
      m = i    
  return m
```
```python {*}{lines:true}
def find_max2(a):
  return max(a)
```
```python {*}{lines:true}
def foo(N):
  s = 0
  n = N
  while n > 0:
    for _ in range(n):
      s += 1
    n //= 2
  return s
```

---

# 1. Stack

> [Stack](https://dictionary.cambridge.org/zht/%E8%A9%9E%E5%85%B8/%E8%8B%B1%E8%AA%9E-%E6%BC%A2%E8%AA%9E-%E7%B0%A1%E9%AB%94/stack): a pile of things arranged one on top of another.

<div class="flex">
    <img src="/week3/book.png" alt="stack of books" width="200">
    <img src="/week3/tray.png" alt="stack of trays" width="200">
</div>

<div class="flex justify-center items-center h-60px mt-8px">

## Last in, First out (LIFO)

</div>

---

## Formal Definition of Stack <arcticons-define />

`Stack` is a collection of objects that are inserted and removed according to the <span class="text-red">last-in, first-out (LIFO)</span> policy.

It supports two basic operations:

- `push()`: add a new element
- `pop()`: remove an element

Additionally, it also supports other operations such as `size()`, `isEmpty()`, and `top()`.

---

## Stack in Python <logos-python />

Fortunately, the `list` in Python supports the operations required by stacks.

```python
s = []
s.append('a') # append() works like push()
s.append('b')
s.append('c')
print(len(s))
print(s.pop())
print(s.pop())
print(len(s))
```

## Stack in Object-Oriented Way <arcticons-lego-builder />

```python
s = Stack()
s.push('a')
s.push('b')
s.push('c')
print(s.size())
print(s.pop())
print(s.pop())
print(s.size())
```

---

## Brainstorm (1) <arcticons-emoji-brain />

Since `list` in Python can be used as a stack, why to bother design our own stack?

<!--
Encapsulation: can limit operations on the stack, exposing only stack-specific methods like push, pop, peek, preventing misuse
Semantic clarity: makes the code more readable, clearly indicating it's a stack structure
Extensibility: can add extra functionality as needed, such as getting stack size or checking if it's empty
Error handling: can customize error handling for operations like popping from an empty stack
-->
<v-click>

## Implementation <arcticons-code-editor />

We use `list` as the underlying data structure.

```python
class Stack:
    def __init__(self):
        self._data = []
    def push(self, item):
        pass
    def pop(self):
        pass
    def size(self):
        pass
    def is_empty(self):
        pass
```

Note that `len(s)` is, in fact, a more <span class="text-red">Pythonic</span> way to get the size of a collection.
</v-click>

---

## Brainstorm (2) <arcticons-emoji-brain />

What will happen if we pop from an empty stack?

```python
s = Stack()
print(s.pop())
```

<v-click>

## Exception <flat-color-icons-disclaimer />

You need also consider the condition that cannot be handled by the "normal flow".

```python
class NoElement(Exception):
    """Error attempting to access an element from an empty collection."""
    pass

def pop(self):
    if self.is_empty():
        raise NoElement('Pop from empty stack!')
    return self._data.pop()
```

</v-click>

---

## Exception <flat-color-icons-disclaimer />

Yet another philosophy to handle exception in addition to `look-before-you-leap`.

```python
def pop(self):
  try:
    return self._data.pop()
  except:
    raise NoElement('Pop from empty stack!')
```

---

# 2. Time Complexity

| Operation    | Complexity |
| ------------ | ---------- |
| `push()`     | $O(1)$     |
| `pop()`      | $O(1)$     |
| `size()`     | $O(1)$     |
| `is_empty()` | $O(1)$     |

Note that the time complexity of `push()` and `pop()` is [amortized](https://dictionary.cambridge.org/zht/%E8%A9%9E%E5%85%B8/%E8%8B%B1%E8%AA%9E-%E6%BC%A2%E8%AA%9E-%E7%B9%81%E9%AB%94/amortize).

> Basically, an **amortized time** is the average time taken per operation, if you do many operations.

To understand [`amortized time`](https://en.wikipedia.org/wiki/Amortized_analysis), you have to know how `list` in Python resizes.

---

## Capacity vs. Size <flat-color-icons-biotech />

In the last step, there is no more space available, so it will be **enlarged** to a new list with capacity of 8, and all old elements will be copied into the new list. Thus, the time complexity is $O(n)$.

<img src="/week3/array-resize.svg" width="600" />

<!--
Each cell of an array must use the same number of bytes.

Referential array

Consecutive memory
  -->

---

## Capacity vs. Size <flat-color-icons-biotech />

Assume the capacity is 1 initially, and it will be enlarged to a new list with $2 \times$capacity. So, to insert $n = 2^k$ elements, the total cost is:

$$2^k + \sum_{i=1}^{k}{2^i} = 3\times 2^k - 2$$

Thus, the average cost per insertion (i.e, amortized time) is:

$$\frac{3 \times 2^k -2}{2^k} = O(1) $$

---

## Measuring in Python <logos-python />

<div class="grid grid-cols-12">

  <div class="col-span-6">

```python
from time import time


def compute_amorized(n):
    data = []
    start = time()
    for _ in range(n):
        data.append(None)
    end = time()
    # in us
    return ((end - start) / n) * 10**6


if __name__ == "__main__":
    for i in [1000, 10000, 100000, 1000000]:
        print(i, compute_amorized(i))
```

  </div>

  <div class="col-span-6">

In my desktop, the output:

| Size    | Time                 |
| ------- | -------------------- |
| 1000    | 0.02002716064453125  |
| 10000   | 0.018644332885742188 |
| 100000  | 0.015821456909179688 |
| 1000000 | 0.015034437179565431 |

  </div>

</div>

---

## Resizing in Python <logos-python />

In Python, `sys.getsizeof()` returns the actual size in bytes.

<div class="grid grid-cols-12">

  <div class="col-span-6">

```python
import sys

data = []
for _ in range(20):
    a = len(data)
    b = sys.getsizeof(data)
    print(f"Length: {a:3d}, Size: {b:4d}")
    data.append(None)
```

<div class="grid grid-cols-6">
   <div class="col-span-2">

| Length | Size                             |
| ------ | -------------------------------- |
| 0      | <span class="text-red">56</span> |
| 1      | <span class="text-red">88</span> |
| 2      | 88                               |
| 3      | 88                               |

   </div>

  <div class="col-span-1">

  </div>

   <div class="col-span-2">

| Length | Size                              |
| ------ | --------------------------------- |
| 4      | 88                                |
| 5      | <span class="text-red">120</span> |
| 6      | 120                               |
| 7      | 120                               |

   </div>
</div>

  </div>

  <div class="col-span-2">

| Length | Size                              |
| ------ | --------------------------------- |
| 8      | 120                               |
| 9      | <span class="text-red">184</span> |
| 10     | 184                               |
| 11     | 184                               |
| 12     | 184                               |
| 13     | 184                               |

  </div>

  <div class="col-span-1">

  </div>

  <div class="col-span-2">

| Length | Size                              |
| ------ | --------------------------------- |
| 14     | 184                               |
| 15     | 184                               |
| 16     | 184                               |
| 17     | <span class="text-red">248</span> |
| 18     | 248                               |
| 19     | 248                               |

  </div>

</div>

---

## Design: Front or Back? <logos-ant-design />

Alice devises a new implementation for stacks based on the `list`.

- `push()`: insert the new element in the <span class="text-red">front</span> of the `list`
- `pop()`: remove and return the <span class="text-red">first</span> element of the `list`

How do you think of her idea?

<div class="flex">
    <div>

```python
s = []
s.insert(0, 'a')
s.insert(0, 'b')
s.insert(0, 'c')
print(s.pop(0)) # 'c'
print(s.pop(0)) # 'b'

```

  </div>
<img src="/week3/right-shift.svg" width="300" />
</div>

---

# 3. Example

## 1). Matching Delimiters <arcticons-musixmatch />

An important task when processing arithmetic expressions is to make sure their delimiting symbols (e.g, `[(5+x)-(y+z)]`) match up correctly.

Here we assume only parentheses `()`, braces `{}`, and brackets `[]` are allowed.

- Correct: `()(()){([])}`
- Incorrect: `({[])`

## Key Point <arcticons-key />

The openings are pushed into a stack, and when a closing is encountered, it must match the opening at the top of the stack.

---

## Code <arcticons-code-editor />

This implementation depends on [stack.py](https://github.com/ChenZhongPu/data-structure-swufe/blob/master/code/python/stack-queue/stack.py).

```python
def is_matched(expr):
    openings = '([{'
    closings = ')]}'
    s = Stack()
    for c in expr:
        if c in openings:
            s.push(c)
        elif c in closings:
            if s.is_empty():
                return False
            if TODO:
                return False
    return s.is_empty()
```

```python
if closings.index(c) != openings.index(s.pop())
  pass
```

What is the time complexity of this algorithm?

---

## 2). Program Execution <logos-c />

A `stack` in the context of executing programs, often referred to as the `call stack`, is a fundamental data structure used in most programming languages to manage function calls and execution flow.

```python

>>> dis.dis('a[i] + b')
  0           0 RESUME                   0

  1           2 LOAD_NAME                0 (a)
              4 LOAD_NAME                1 (i)
              6 BINARY_SUBSCR
             10 LOAD_NAME                2 (b)
             12 BINARY_OP                0 (+)
             16 RETURN_VALUE
```

---

# Conclusion

- Stack
- [Array](https://en.wikipedia.org/wiki/Array_(data_structure)) Resizing
- Python `list` in-depth
- [Python TimeComplexity](https://wiki.python.org/moin/TimeComplexity)

## Task <arcticons-google-tasks />

- Read Chapter 5 carefully, and it will enhance your programming skills greatly
- Try to solve C-6.22.
