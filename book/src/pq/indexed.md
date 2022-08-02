# Index Priority Queue
In fact, when designing a priority queue, we shall assume that **the keys are immutable**. In other words, the client code does not change the keys; otherwise, it might invalidate the heap-order invariant. For example, `b2` will be the first (*root*) element:

```java
// assume books are compared by their prices
MaxPQ<Book> qp = new MaxPQ<Book>();
Book b1 = new Book("Gone with the wind", 89, "Margaret Mitchell");
Book b2 = new Book("Data structures", 120, "Unknown");
Book b3 = new Book("The old man and the sea", 36, "Ernest Hemingway");
pq.insert(b1);
pq.insert(b2);
pq.insert(b3);
```

But what if the sellers offer a 50% discount on `b2`? You may expect `b1` will become the first (*root*) element. However, the *reheapifying* process will not happen automatically. One common solution to this is an **index priority queue**.

An *index priority queue* assigns an *index* as the *position* to the element, and thus we can retrieve the element through the index, and we can even change the priority during runtime. In what follows, we will implement an `IndexedMinPQ`, and the maximum-oriented version is similar. For brevity, the error handling code is omitted.

## IndexMinPQ
We mainly use the array as the underlying data structure:

- `Key[] keys`: `keys[i]` is the priority of `i`
- `int[] pq`: binary heap using 1-based indexing
- `int[] qp`: reverse of `pq`, so `qp[pq[i]] = pq[qp[i]] = i`
- `n`: the size of the heap

Given the heap `pq`, the elements will be compared by `keys[pq[i]]`, instead of `pq[i]`. As a result, the minimal key is `keys[pq[1]]`. To insert a *key* with an associated index *i*:

```java
public void insert(int i, Key key) {
    n++;
    qp[i] = n;
    pq[n] = i;
    keys[i] = key;
    swim(n);
}
```

It means that the *n*-th location of the heap (`pq`) stores the *key*'s associated index *i*. Note that the map from *i* to *n* can be returned via the reverse array `qp`. After swimming, both `pq` and `qp` might change. Let's recap the connections again:

- `keys`: a *key* has an associated index *i*
- `pq`: the heap whose position *j* stores index *i*, and the comparator is based on `keys[i]`
- `qp`: the reverse array of `pq`

Readers can try to implement the following methods, and the complete code can be found at [IndeMinPQ.java](https://github.com/ChenZhongPu/data-structure-swufe/blob/master/code/java/pq/src/main/java/org/swufe/datastructures/IndeMinPQ.java) and [index_min_pq.py](https://github.com/ChenZhongPu/data-structure-swufe/blob/master/code/python/pq/index_min_pq.py).

```java
public int delMin() {
    // remove the minimum key and return its index
}

public void changeKey(int i, Key key) {
    // change the key associated with index `i` to `key`
}
```