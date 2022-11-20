# Exercise
1. Please implement a BST which contains both *keys* and *values*. In other words, it works like a *map*, and it supports:

- `put(key, value)`: If `key` is found, update its associated value to `value`; otherwise, insert a new node.
- `get(key)`: Return value associated with `key`; return `null` if key is not present.
- `delete(key)`: Delete the node whose key is `key`; do nothing if key is not present.

---
2. When updating (i.e., `put()` or `delete()`) a BST, it is required that the *binary-search-tree property* holds true. Please design an algorithm to check whether a given BST is valid.

---
3. In our implementation of `remove()` of a BST, `x` can be replaced with the leftmost (i.e, *min*) node `y` in the tree rooted at `x.right`. Alternatively, `x` can also be replaced with the the rightmost (i.e., *max*) node `z` in the tree rooted at `x.left`. Essentially, `z` is the predecessor of `x`. Please design an algorithm using this idea.


              10                              8
            /     \         delete(10)      /    \
          7       15       --------->     7       15 
        /  \    /   \                    /       /  \ 
       5    8  11   18                 5        11   18

---
4. Please implement `removeMax(x, key)` which returns a new root for a BST in an iterative way.

---
5. Ordered-based operations.

- Please implement all order-based operations for a BST introduced in [Binary Search Tree (2)](./bst2.md).
- Can you implement them in an iterative way?

---
6. Please argue that inserting a new key into a RBT will never violate Property 1, 3 and 4.

---
7. Try to implement a RBT with a global `NIL` object.

---
8. Please plot the height of a RBT with random *N* keys.

---
9. A node `x` is inserted into a RBT with `put()` and then is immediately deleted with `remove()`. Is the resulting tree always the same as the initial red-black tree? Justify your answer.

---
10. If we didn't introduce `NIL` object, then how to handle the case when `x` is `null` for the deletion on a RBT?

---
11. Draw the RBT that results when you insert items with the keys (10, 2, 30, 40, 26, 32, 11, 18) in that order into an initially empty tree.

---
12. Please design an algorithm to check whether a given RBT is valid.

---
13. A [k-d tree](https://en.wikipedia.org/wiki/K-d_tree) (short for k-dimensional tree) is a space-partitioning data structure for organizing points in a k-dimensional space.

Please complete the code based on [kd_tree.py](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/python/tree/kd_tree.py) or [KDTree.java](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/java/tree/KDTree.java). 