---
theme: seriph
title: Data Structures-Week9
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

## Lecture 9: Balanced Search Trees

CHEN Zhongpu, Fall 2024

<div class="text-10px">
School of Computing and Artificial Intelligence, SWUFE
</div>
<div class="flex justify-center items-center h-60px mt-8px">
    <img src="/swufefull.svg" class="h-full" alt="swufe logo"/>
</div>

---

## Review

1. Try to verify the correctness of the following proposition:

> Search hits in a BST built from $N$ random keys require about $\log N$ (about $1.39\log N$) compares on the average.

```python
def height(self):
    def _height(x: BST.Node):
        if x is None:
            return -1
        else:
            return 1 + max(_height(x.left), _height(x.right))

    return _height(self._root)
```

2. What is the height of a BST if inserting 1, 2, 4, 7, 8, and 10 sequentially?

<div class="flex justify-center items-center h-200px mt-8px" v-click>
    <img src="/week9/linear.png"  class="h-full" alt="node"/>
</div>

---

# 1. Balanced Search Trees

<div class="flex justify-center items-center h-240px mt-8px">
    <img src="/week9/linear.png"  class="h-full" alt="node"/>
</div>

> Because most operations on a BST take time directly proportional to height of the tree, so it is desirable to keep the height small.

### Informal Definition

A search tree is **balanced** if the height is guaranteed to be $\log N$.

---

## Why $\log N$?

A binary tree with height $h$ can contain at most:

$$2^0 + 2^1 + \cdots + 2^h = 2^{h+1} + 1$$

It follows that for any tree wiht $N$ nodes and height $h$, we have:

$$n \leq 2^{h+1} - 1$$

And that implies:

$$h \geq ceil(\log{(N + 1)} - 1) \geq floor(\log{N})$$

In other words, the minimum height of a binary tree with $N$ nodes is $\log N$.

---

# 1. AVL Trees

An AVL Tree (named after its inventors Adelson-Velsky and Landis) is a self-balancing binary search tree.

> A self-balancing BST is any node-based binary search tree that automatically keeps its height small in the face of arbitrary item insertions and deletions.

<v-click>

### AVL Balance Property <arcticons-angry-birds />

In an AVL tree, the heights of the two child subtrees of any node differ by **at most one**.

Question: Is this an AVL Tree? <arcticons-symbol-question-mark />

<div class="flex justify-center items-center h-240px mt-8px">
    <img src="/week9/unbalanced.png"  class="h-full" alt="node"/>
</div>

</v-click>

---

## 1.1 Height of AVL Tree

We use $N(h)$ to denote the minimum number of nodes in an AVL tree of height $h$. Then we have:

$$
 N(h) \geq
 \begin{cases}
  0,             & h = -1   \\
  1,             & h = 0    \\
  1 + 2N(h - 2), & h \geq 1 \\
 \end{cases}
$$

So, when $h \geq 1$, we have $N(h) \geq 2N(h - 2)$. It follows that $N(h) \geq 2^{h/2}$. After taking logarithm, we have:

$$h < 2\log N(h)$$

---

## 1.2 Design of AVL Tree

A feasible way to design an AVL node is to maintain the height of each node.

```python
class Node:
    def __init__(self, key, height=0,
                 left=None, right=None):
        self.key = key
        self.height = height
        self.left = left
        self.right = right

class AVL:
    def __init__(self):
        self.root = None
```

### Balance Factor <arcticons-questionnaire-star />

We can introduce **balance factor** to indicate the balance of a node, which is defined as:

$$BF(x) = h(x.left) - h(x.right)$$

What are possible values of $BF(x)$? <arcticons-symbol-question-mark />

---

## 1.3 `get()` in AVL tree

It works exactly the same as in a normal BST.

```python
def get(self, key):
    def _get(x: Node):
        if x is None or key == x.key:
            return x
        if key < x.key:
            return _get(x.left)
        else:
            return _get(x.right)
    if key is None:
        raise KeyError
    return _get(self._root)
```

---

## 1.4 `put()` in AVL tree

What happens when we insert 1 into this AVL tree?

<div class="flex justify-center items-center h-280px mt-8px">
    <img src="/week9/put.png"  class="h-full" alt="node"/>
</div>

<v-click>

> Modifying an AVL tree may violate the balance property, so we need to **rebalance** the tree.

</v-click>

---

### Rotations <arcticons-auto-auto-rotate />

Re-balancing an AVL tree is done by performing **rotations**.

<div class="flex justify-center items-center h-240px mt-8px">
    <img src="/week9/rotate.png"  class="h-full" alt="node"/>
</div>

There are two types of rotations: _left rotation_ and _right rotation_.

> It is clear that tree rotations still maintain the BST property.

---

## 1.5 Right Rotation

<div class="grid grid-cols-12">
  <div class="col-span-5">

<div class="flex justify-center items-center h-200px mt-8px">
    <img src="/week9/rotate.png"  class="h-full" alt="node"/>
</div>

  </div>

  <div class="col-span-7">

```python
def _right_rotate(y: Node):
    x = y.left
    y.left = x.right
    x.right = y
    y.height = max(AVL._get_height(y.left),
                   AVL._get_height(y.right)) + 1
    x.height = max(AVL._get_height(x.left),
                   AVL._get_height(x.right)) + 1
    return x
```

  </div>

</div>

```python
def _get_height(node: Node):
    if node is None:
        return -1
    else:
        return node.height
```

Please implement the _left rotation_.

---

## 1.6 Re-balance

> Re-balance if and only if the _balance factor_ is 2 or -2.

### Case 1: Left-Left <arcticons-3dmark />

If bf(x) = 2 and key < x.left.key, then we need to do a right rotation on x.

<div class="grid grid-cols-12">
  <div class="col-span-5">

<div class="flex justify-center items-center h-200px mt-8px">
    <img src="/week9/right-rotate.png"  class="h-full" alt="node"/>
</div>

  </div>

  <div class="col-span-7">

<div class="flex justify-center items-center h-200px mt-8px">
    <img src="/week9/right-example.png"  class="h-full" alt="node"/>
</div>

  </div>
</div>

This [video](https://www.youtube.com/watch?v=nngoekT_2z4) shows how to perform a right rotation on an AVL tree.

---

### Case 2: Right-Right <arcticons-3dmark />

If bf(x) = -2 and key > x.right.key, then we need to do a left rotation on x.

<div class="flex justify-center items-center h-300px mt-8px">
    <img src="/week9/left-rotate.png"  class="h-full" alt="node"/>
</div>

This [video](https://youtu.be/r6ifm2a1Ur8) shows how to perform a left rotation on an AVL tree.

---

### Case 3: Left-Right <arcticons-3dmark />

If bf(x) = 2 and key > x.left.key, then we need to do a left rotation on x.left and make a right rotation on x.

<div class="flex justify-center items-center h-300px mt-8px">
    <img src="/week9/left-right.png"  class="h-full" alt="node"/>
</div>

After a left rotation on x.left, it becomes Case 1 (Left-Left).

---

### Case 3: Left-Right <arcticons-3dmark />

<div class="flex justify-center items-center h-220px mt-8px">
    <img src="/week9/left-right-1.png"  class="h-full" alt="node"/>
</div>

<div class="flex justify-center items-center h-220px mt-8px">
    <img src="/week9/left-right-2.png"  class="h-full" alt="node"/>
</div>

---

### Case 4: Right-Left <arcticons-3dmark />

If bf(x) = -2 and key < x.right.key, then we need to do a right rotation on x.right and make a left rotation on x.

<div class="flex justify-center items-center h-300px mt-8px">
    <img src="/week9/right-left.png"  class="h-full" alt="node"/>
</div>

---

## 1.7 `put()` in AVL tree

```python
def _put(x: AVL.Node):
    if x is None:
        return AVL.Node(key)
    elif key < x.key:
        x.left = _put(x.left)
    elif key > x.key:
        x.right = _put(x.right)
    # update the height
    x.height = max(AVL._get_height(x.left), AVL._get_height(x.right)) + 1
    # get the balance factor
    bf = AVL._get_balance_factor(x)
    if bf > 1 and key < x.left.key:  # case 1
        return AVL._right_rotate(x)
    if bf < -1 and key > x.right.key:  # case 2
        return AVL._left_rotate(x)
    if bf > 1 and key > x.left.key:  # case 3
        x.left = AVL._left_rotate(x.left)
        return AVL._right_rotate(x)
    if bf < -1 and key < x.right.key:  # case 4
        x.right = AVL._right_rotate(x.right)
        return AVL._left_rotate(x)
    return x
```

---

## 1.8 `delete()` in AVL tree

What happens when we delete 11 from this AVL tree?

<div class="flex justify-center items-center h-280px mt-8px">
    <img src="/week9/delete.png"  class="h-full" alt="node"/>
</div>

---

# 2. Red-Black Trees

Red-black trees are a kind of self-balancing binary search tree with one extra bit of storage per node: its color, which can be either <span class="text-red">red</span> or black.

## Red-black tree properties

1. Every node is either red or black.
2. The root is black.
3. Every leaf (NIL) is black.
4. If a red node has children, then the children are black.
5. For each node, all simple paths from the node to descendant leaves contain the same number of black nodes.

---

## 2.1 Example of Red-Black Tree

<div class="flex justify-center items-center h-300px mt-8px">
    <img src="/week9/rb-example.png"  class="h-full" alt="node"/>
</div>

---

## 2.2 Black Height

The number of black nodes on any simple path from, but not including, a node $x$ down to a leaf is called the **black-height** of the node, denoted by $bh(x)$.

<div class="flex justify-center items-center h-260px mt-8px">
    <img src="/week9/rb.png"  class="h-full" alt="node"/>
</div>

> 5. For each node, all simple paths from the node to descendant leaves contain the same number of black nodes.

---

## 2.3 Exercise

<div class="flex justify-center items-center h-460px mt-8px">
    <img src="/week9/rb-insert.png"  class="h-full" alt="node"/>
</div>

---

# Summery

- Balanced search trees
- AVL trees, Red-black trees
- Rotations

---

# Cost Summary

| Algorithm (data structure)                                                    | Search (worst case) | Insert (worst case) | Search (average case) | Insert (average case) | Efficiently ordered operations |
| ----------------------------------------------------------------------------- | ------------------- | ------------------- | --------------------- | --------------------- | ------------------------------ |
| <span class="text-red">sequential search (unordered array/linked list)</span> | $N$                 | $N$                 | $N/2$                 | $N$                   | no                             |
| <span class="text-red">binary search (ordered array)</span>                   | $\log N$            | $N$                 | $\log N$              | $N/2$                 | yes                            |
| <span class="text-red">BST</span>                                             | $N$                 | $N$                 | $1.39\log N$          | $1.39\log N$          | yes                            |
| <span class="text-red">Red-black tree</span>                                  | $2\log N$           | $2\log N$           | $\log N$              | $\log N$              | yes                            |
