# PQ Implementation (2)
We represent a heap of size *N* in private array `pq[]` of length *N + 1*, with `pq[0]` unused. 

To make it easy to describe the algorithm and hide the differences in different languages, we further design two helper methods:

- `less(i, j)`: to test if `qp[i]` is less than `qp[j]`
- `swap(i, j)`: to swap (exchange) `qp[i]` and `qp[j]`

To compare two values, we can use [Comparable](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Comparable.html)'s `compareTo()` method in Java, and the built-in `<` operator in Python. To swap two values,

```java
private void swap(int i, int j) {
    Key t = pq[i];
    qp[i] = qp[j];
    qp[j] = t;
}
```

```python
def _swap(self, i, j):
    self._pq[i], self._qp[j] = self._pq[j], self._qp[i] 
```

Like the modifications on a BST, heap operations could violate the *heap property*, and we need to fix it. We refer to this process as *reheapifying*, or *restoring*. 

There are two cases:

- For some node with a high priority, we have to travel *up* the heap.
- For some node with a low priority, we have to travel *down* the heap.

## Bottom-up reheapify (swim)
If the heap order is violated because a node's key becomes larger than its parent's key, then we can fix the violation by exchanging the node with its parent by swimming up.

<img src="image/swim.png" width="70%">

To justify the method’s name, we think of the new node, having too large a key, as having to *swim* to a higher level in the heap.

<img src="image/alg-swim.png" width="80%">

## Top-down reheapify (sink)
If the heap order is violated because a node's key becomes *smaller* than one of both of that node's children's keys, then we can make progress toward fixing the violation by exchanging the node with the larger of its two children. This switch may cause a violation at the child; we fix that violation in the same way by moving down the heap.

<img src="image/sink.png" width="70%">

To justify the method’s name, we think of the new node, having too small a key, as having to *sink* to a lower level in the heap.

<img src="image/alg-sink.png" width="80%">

## `insert()`
We add the new key at the end of the array, increment the size of the heap, and then swim up through the heap with that key to restore the heap property.

```java
public void insert(Key v) {
    pq[++N] = v;
    swim(N);
}
```

<img src="image/insert.png" width="70%">

## `delMax()`
The exchange the last key with the root (whose key is the largest), decrement the size of the heap, and then sink down through the heap with that key to restore the heap property.

```java
public Key delMax() {
    Key max = pq[1];
    swap(1, N--);
    pq[N+1] = null;
    sink(1);
    return max;
}
```

<img src="image/remove.png" width="70%">

## A few notes on implementations
The complete code can be found at [MaxPQ.java](https://github.com/ChenZhongPu/data-structure-swufe/blob/master/code/java/pq/src/main/java/org/swufe/datastructures/MaxPQ.java) and [max_pq.py](https://github.com/ChenZhongPu/data-structure-swufe/blob/master/code/python/pq/max_pq.py).

### Python
Since the first position (i.e., index 0) is unused, we can append `None` as a dummy at index 0.

```python
def __init__(self):
    self._pq = []
    self._pq.append(None)
```

And the size of the heap is `len(self._pq) - 1`.

### Java
If we use [ArrayList](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayList.html) which has the resizing capability, the code is similar to the Python version. Instead, I choose the built-in array type and implement the resizing method by ourselves, as we did for stacks.
