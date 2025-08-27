---
theme: seriph
title: Data Structures-Week7
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

## Lecture 7: Trees

CHEN Zhongpu, Fall 2025

<div class="text-10px">
School of Computing and Artificial Intelligence, SWUFE
</div>
<div class="flex justify-center items-center h-60px mt-8px">
    <img src="/swufefull.svg" class="h-full" alt="swufe logo"/>
</div>

---

# Review

Linked lists and arrays work well for _linear_ data structures, but they are not suitable for _hierarchical_ data structures.

## Example <arcticons-animal-kingdom />

Bob's father is Jack, and he has a sister named Alice. How can we represent this information?

<div class="flex justify-center items-center h-240px mt-8px">
    <img src="/week7/animal.webp"  class="h-full" alt="family"/>
</div>

---

# 1. Tree

In computer science, a tree is a widely used ADT that represents a hierarchical tree structure with a set of connected nodes.

<div class="flex">
  <div>
    <img src="/week7/html.png" alt="tree" width="300">
  </div>
  <div>

```html
<!doctype html>
<html>
  <head>
    <title>My Web Page</title>
  </head>
  <body>
    <h1>Data Structures</h1>
    <img src="tree.png" alt="tree" />
  </body>
</html>
```

  </div>
</div>

This definition reveals the **parent-children** relationship between nodes in trees.

---

## Definition <arcticons-emoji-evergreen-tree />

A tree can be defined in a **recursive** way: a tree `T` is either _empty_ or consists of a node `r`, called the **root** of `T`, and a (possibly empty) set of _subtrees_ whose roots are the children of `r`.

<div class="flex justify-center items-center h-240px mt-8px">
    <img src="/week7/html.png"  class="h-full" alt="tree"/>
</div>

---

## Terminology <arcticons-todotree />

<div class="flex justify-center items-center h-380px mt-8px">
    <img src="/week7/tree.png"  class="h-full" alt="tree"/>
</div>

---

### Path, Simple Path <arcticons-sente-online-go />

A **path** is a sequence of nodes with the property that each node in the sequence is adjacent to the node linked to it. A path that does not repeat nodes is called a _simple path_.

<div class="flex justify-center items-center h-340px mt-8px">
    <img src="/week7/path.png"  class="h-full" alt="path"/>
</div>

---

### Height <arcticons-treecard />

The **height** of a node is the length of the longest downward path to a leaf from that node. <span class="text-red">The height of an empty tree is -1.</span>

$$height(T) = height(T.root)$$

<div class="flex justify-center items-center h-320px mt-8px">
    <img src="/week7/path.png"  class="h-full" alt="path"/>
</div>

---

# 2. Nodes

Generally, every node has links to its parent and children, and holds a _key_ and an associated _value_ (a.k.a., satellite data).

```python
class Node:
  def __init__(self, parent, children, key, value):
      self._parent = parent
      self._children = children
      self._key = key
      self._value = value
```

For simplicity, we only consider **keys** in this lecture.

<div class="flex justify-center items-center h-200px mt-8px">
    <img src="/week7/bst.png"  class="h-full" alt="node"/>
</div>

---

## Warning <arcticons-foss-warn />

But a tree without constraints has little value in practice. We need to impose some restrictions on the structure of trees.

- **Binary search tree (BST)**
- **Balanced binary search tree**
- **AVL tree**, **Red-black tree**

---

# 3. Binary Trees

> 📘 Binary Tree
>
> Each node has exactly two links, which are called its **left** and **right** links, that point to nodes called its _left child_ and _right child_, respectively.

<div class="flex justify-center items-center h-330px mt-8px">
    <img src="/week7/binary.png"  class="h-full" alt="node"/>
</div>

---

# 4. Binary Search Trees

> 📘 Binary Search Tree
>
> A binary search tree (BST) is a binary tree where each node has a (_comparable_) key and satisfies the restriction that the key in any node is larger than the keys in all nodes in that node's left subtree and smaller than the keys in all nodes in that node's right subtree.

The <span class="text-red">binary-search-tree property</span>: Let `x` be a node in a BST.

- If `y` is a node in the left subtree of `x`, then `y.key < x.key`.
- If `y` is a node in the right subtree of `x`, then `y.key > x.key`.

<div class="flex justify-center items-center mt-8px">
  <div class="flex items-center">
    <img src="/week7/bst.png" class="h-60 mr-4 object-contain" alt="node"/>
    <p class="max-w-md">
      Thanks to the <strong>binary-search-tree property</strong>, it supports efficient search. For instance, how to search 6?
    </p>
  </div>
</div>

---

## Exercise <arcticons-blackberrydevicesearch />

Is this a valid BST?

<div class="flex justify-center items-center h-240px mt-8px">
    <img src="/week7/invalid.png"  class="h-full" alt="node"/>
</div>

---

## 4.1 BST: Structure

```python
class Node:
    def __init__(self, key,
            left=None, right=None):
        self.key = key
        self.left = left
        self.right = right

class BST:
    def __init__(self):
        self.root = None
```

---

## 4.2 BST: get()

This method is often called `search()`. It is used to get the node containing the given key.

<div class="flex justify-center items-center h-240px mt-8px">
    <img src="/week7/get.png"  class="h-full" alt="node"/>
</div>

The core idea: starting from _root_, trace a simple path downward in the tree until meeting the _key_ or _null_.

---

### Iterative get()

<div class="flex justify-center items-center h-300px mt-8px">
    <img src="/week7/get_alg.png"  class="h-full" alt="node"/>
</div>

- What is time complexity of `get()`?
- How to implement `contains()` based on `get()`?

---

### Recursive get()

- **Base step**: `key` is found or `null` is reached.
- **Recursive step**: search in left or right subtree.

<div class="flex justify-center items-center h-300px mt-8px">
    <img src="/week7/get_alg2.png"  class="h-full" alt="node"/>
</div>

---

## 4.3 BST: put()

This method is often called `insert()`. It is used to insert a new key.

<div class="flex justify-center items-center h-260px mt-8px">
    <img src="/week7/put.png"  class="h-full" alt="node"/>
</div>

The core idea: it works like `get()` by inserting the new key as a leaf node.

---

### Iterative put()

<div class="flex justify-center items-center h-380px mt-8px">
    <img src="/week7/put_alg.png"  class="h-full" alt="node"/>
</div>

`y` is the parent of `x` (`z`).

---

### Exercise

Please draw the BST after inserting 5, 2, 4, 10, 8, 7, 42, in that order into an empty BST.

---

### Recursive put()

`put(x, key)`: to insert a new key into a BST rooted at `x`, and then return the root.

<div class="flex justify-center items-center h-300px mt-8px">
    <img src="/week7/put_alg2.png"  class="h-full" alt="node"/>
</div>

---

### Exercise

How to count the number of nodes (i.e., `size()`) in a BST using a recursive implementation?

---

## 4.4 BST: Ordered Operations

A BST supports a myriad of ordered-based operations, such as `min()`, `max()`, `floor()`, and `ceiling()`.

<div class="flex justify-center items-center h-300px mt-8px">
    <img src="/week7/bst.png"  class="h-full" alt="node"/>
</div>

What is the maximum key in the BST? Where does it locate?

---

### max()

Note that the maximum key is always located at the **rightmost** in a BST.

<div class="flex justify-center items-center h-400px mt-8px">
    <img src="/week7/max_alg.png"  class="h-full" alt="node"/>
</div>

---

### Recursive max()

<div class="flex justify-center items-center h-300px mt-8px">
    <img src="/week7/max_alg2.png"  class="h-full" alt="node"/>
</div>

Try to implement `min()` by yourself.

---

### floor()

`floor(key)`: the largest key in the BST that is less than or equal to `key`.

<div class="flex justify-center items-center h-300px mt-8px">
    <img src="/week7/bst.png"  class="h-full" alt="node"/>
</div>

- `floor(5)`: 4; `floor(6)`: 6; `floor(7)`: 6; `floor(15)`: 14
- `floor(0)`: None

---

### Recursive floor()

`_floor(x, key)`: the largest key in the BST rooted at `x` that is less than or equal to `key`.

<div class="flex justify-center items-center h-220px mt-50px">
    <img src="/week7/bst.png"  class="h-full" alt="node"/>

```python
def _floor(x: BST.Node | None, key):
    if x is None:
        return None
    if key == x.key:
        return x.key
    if key < x.key:
        return _floor(x.left)
    t = _floor(x.right)
    if t is not None:
        return t
    else:
        return x
```

</div>

```python
def floor(self, key):
    t = _floor(self._root)
    if t is None:
        raise NoElement
    return t.key
```

---

### Iterative floor()

```python
def floor(self, key):
    """Iterative version of floor."""
    x = self._root
    best = None
    while x is not None:
        if key == x.key:
            return x.key
        if key < x.key:
            x = x.left
        else:
            best = x
            x = x.right
    if best is None:
        raise NoElement
    return best.key
```

Try to implement `ceiling()` by yourself.

---

## 4.5 BST: removeMin()

To remove the node with the minimum key. Note that it is always located at the **leftmost** in a BST.

<div class="flex justify-center items-center h-300px mt-8px">
    <img src="/week7/bst.png"  class="h-full" alt="node"/>
</div>

To delete 1, to make 3's left be 1's right.

---

### removeMin()

<div class="flex justify-center items-center h-400px mt-8px">
    <img src="/week7/remove_min_alg.png"  class="h-full" alt="node"/>
</div>

---

### Recursive removeMin()

```python
def _remove_min(x: Node):
    if x.left is None:
        return x.right
    x.left = BST._remove_min(x.left)
    return x

def remove_min(self):
    if self.is_empty():
        raise NoElement
    self._root = BST._remove_min(self._root)
```

---

### Recursive remove()

`remove(x)` removes the node with key `x` from the BST rooted at `x`, and then returns the new root.

### Case 0: `x` has no child, return `null`

### Case 1: `x` has one child, return the other child

<div class="flex justify-center items-center h-300px mt-8px">
    <img src="/week7/remove_1.png"  class="h-full" alt="node"/>
</div>

---

### Recursive remove()

`remove(x)` removes the node with key `x` from the BST rooted at `x`, and then returns the new root.

### Case 2: `x` has two children, return the successor of `x` (i.e., `min(x.right)`), and then remove the successor (`removeMin(x.right)`)

<div class="flex justify-center items-center h-300px mt-8px">
    <img src="/week7/remove_2.png"  class="h-full" alt="node"/>
</div>

---

```python
def remove(self, key):
    def _remove(x: BST.Node):
        if x is None:
            return None
        if key < x.key:
            x.left = _remove(x.left)
        elif key > x.key:
            x.right = _remove(x.right)
        else:
            if x.right is None:
                return x.left
            if x.left is None:
                return x.right
            t = x
            x = BST._min(t.right)
            x.right = BST._remove_min(t.right)
            x.left = t.left
        return x
    self._root = _remove(self._root)
```

---

# 5. Tree Transversal

Generally, there are three ways to traverse a tree: **pre-order**, **in-order**, and **post-order**.

- **Pre-order**: root, left, right
- **In-order**: left, root, right
- **Post-order**: left, right, root

<div class="flex justify-center items-center h-300px mt-8px">
    <img src="/week7/bst.png"  class="h-full" alt="node"/>
</div>

---

## 5.1 In-order in BST

If we project the BST into a line, the in-order traversal will sort the keys in ascending order.

<div class="flex justify-center items-center h-400px mt-8px">
    <img src="/week7/inorder.png"  class="h-full" alt="node"/>
</div>

---

### Code

```python
def in_order_print(self):
    def _in_order_print(x: BST.Node):
        if x is None:
            return
        _in_order_print(x.left)
        print(x.key)
        _in_order_print(x.right)

    _in_order_print(self._root)
```

Try to implement pre-order and post-order traversal by yourself.

---

# Conclusion

- Tree
- Binary Search Tree
- Common operations (get, put, remove, min, max, floor, ceiling)
- In-order/Pre-order/Post-order traversal
