# Exercise
1. Please implement Python's counterpart of List's [int lastIndexOf​(Object o)](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html#lastIndexOf(java.lang.Object)).

```python
def last_index_of(a: list, e) -> int:
    pass
```

---
2. Please implement your own *ArrayList* based on an array in Java. To make sure that your APIs are consistent, please let your class implement [java.util.List](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html) interface.


```java
public class MyArrayList<E> implements List<E> {
    ...
}
```

---
3. Try to implement the selection sort algorithm based on the pseudo code using Java or Python.

---
4. Please analyze the time complexity of the selection sort algorithm.

---
5. Try to write the pseudo code of inserting an item at index `i` on a linked list as [set​(int index, E element)](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html#set(int,E)).


---
6. Try to write the pseudo code of clearing a linked list.

---
7. Please design an algorithm to convert a linked list to an array in Python.

---
8. Please try to implement a stack based on singly linked lists using an inner `Node` class in Python.


---
9. Try to implement the `==` operation by overriding `__eq__()` method for a singly linked list in Python. We would say two lists are identical when they have the same data and the arrangement of data is also the same.

```python
a = LinkedList()
a.add_last(1)
a.add_last(3)

b = LinkedList()
b.add_last(1)
b.add_last(3)

assert a == b
```

---
10. Try to override `equals()` method for a singly linked list in Java. We would say two lists are identical when they have the same data and the arrangement of data is also the same.

```java
LinkedList<Integer> a = new LinkedList<>();
a.addLast(1);
a.addLast(3);

LinkedList<Integer> b = new LinkedList<>();
b.addLast(1);
b.addLast(3);

assert a.equals(b);
```

---
11. Try to implement all operations shown in [LinkedListProblems](http://cslibrary.stanford.edu/105/LinkedListProblems.pdf).

---
12. Try to solve [LeetCode: 92. Reverse Linked List II](https://leetcode.com/problems/reverse-linked-list-ii/).

---
13. Try to convert the binary search implementation ([BinarySearch.java](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/java/unit-work/src/main/java/org/swufe/datastructure/BinarySearch.java) or [binary_search.py](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/python/start/binary_search.py)) to a recursive version.

Note that different from the current implementations, it should return the largest index  `i` so that `a[i]` is smaller than or equal to key.  

````py
assert binary_search([1, 3, 8, 10], 3) == 1

assert binary_sarch([1, 3, 8, 10], 8) == 2

assert binary_search([1, 3, 8, 10], 9) == 2
````