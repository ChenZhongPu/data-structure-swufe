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

## Stack based on linked lists
In this subsection, we are going to implement a stack based on linked lists. 

> A stack is a collection of objects that are inserted and removed according to the last-in, first-out (LIFO) policy.