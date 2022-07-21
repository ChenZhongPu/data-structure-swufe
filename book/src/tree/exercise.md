# Exercise
1. Please implement a BST which contains both *keys* and *values*. To be specific, it supports:

- `put(key, value)`: If `key` is found, update its associated value to `value`; otherwise, insert a new node.
- `get(key)`: Return value associated with `key`; return `null` if key is not present.
- `delete(key)`: Delete the node whose key is `key`; do nothing if key is not present.

---
2. When updating (i.e., `put()` or `delete()`) a BST, it is required that the *binary-search-tree property* holds true. Please design an algorithm to check whether a given BST is valid.