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