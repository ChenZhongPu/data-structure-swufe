# Deques in Python
Luckily, [collections.deque](https://docs.python.org/3/library/collections.html#collections.deque) has been designed in Python, which have fast *appends* and *pops* from both ends.

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

As we can see, `append()` is to add to the end, while `appendleft()` is to add to the front; `pop()` is to remove the last, while `popleft()` is to remove the first.

## Deques based on circular arrays
In practice, [collections.deque](https://docs.python.org/3/library/collections.html#collections.deque) is always your top choice. But as for learning, it is meaningful and interesting to implement a deque on our own. The complete code can be found at [array_deque.py](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/python/stack-queue/array_deque.py).

```python
class ArrayDeque:
    """A double-ended queue (deque) based on circular queues."""

    DEFAULT_CAPACITY = 10

    def __init__(self):
        self._data = [None] * ArrayDeque.DEFAULT_CAPACITY
        self._size = 0
        self._front = 0
```

Clearly, `add_last()` is exactly the same with `enqueue()`, and `remove_first()` is exactly the same with `dequeue()`.

As for `as_first()` and `delete_last()`, we rely on mod to circularly decrement the index[^mod]:

```python
def add_first(self, item):
    if self.size() == len(self._data):
        self._resize(2 * len(self._data))
    self._front = (self._front - 1) % len(self._data)
    self._data[self._front] = item
    self._size += 1
```

```python
def delete_last(self):
    if self.is_empty():
        raise NoElement('Remove from empty queue!')
    if self.size() == len(self._data) // 4:
        self._resize(len(self._data) // 2)
    back = (self._front + self._size - 1) % len(self._data)
    answer = self._data[back]
    self._data[back] = None
    self._size -= 1
    return answer
```

## Application: number of recent calls
This application is from [leetcode](https://leetcode.com/problems/number-of-recent-calls/), and readers shall check the question description by themselves.

As for this problem, one possible algorithm is to use `deque`:

```python
from collections import deque


class RecentCounter:
    def __init__(self):
        self.data = deque()

    def ping(self, t: int) -> int:
        while len(self.data) > 0 and (t - 3000) > self.data[0]:
            self.data.popleft()
        self.data.append(t)
        return len(self.data)
```



---
[^mod] `-1 mod n = n - 1`.