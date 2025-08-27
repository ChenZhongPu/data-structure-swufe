---
theme: seriph
title: Data Structures-Week8
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

## Lecture 8: Huffman Trees

CHEN Zhongpu, Fall 2025

<div class="text-10px">
School of Computing and Artificial Intelligence, SWUFE
</div>
<div class="flex justify-center items-center h-60px mt-8px">
    <img src="/swufefull.svg" class="h-full" alt="swufe logo"/>
</div>

---

# Review

- There are many terminologies not covered in the last lecture. Try to understand the definition of `depth` given a tree. What is the difference between the depth of a node and the height of a tree?
- Try to implement the `height()` given a BST.

<div class="flex justify-center items-center h-300px mt-8px">
    <img src="/week7/bst.png"  class="h-full" alt="node"/>
</div>

---

# 1. How to encode a message?

Assume it is case-insensitive and only contains letters.

```
this is an example of a huffman tree
```

## Solution 1: ASCII encoding <arcticons-bilkascango />

It takes 8 bits for each character, so it takes $8 \times 36 = 288$ bits.

> We can use `bin(ord(c))` to get the binary representation of a character.

<arcticons-thinkfree /> No dumb question: Can I use `int` to store a character? For example, `1` for `a`, `2` for `b`, etc.Is it more efficient or less efficient?

<v-click>

## Can we do better? <arcticons-betterhelp />

For example, given `this is an example of a huffman tree`, can we encode it with less bits?
n
There are only **36** characters, in which **16** unique characters, so **4 bits** are enough to represent each character.

</v-click>

---

## Can we do even better? <arcticons-betterhelp />

Given a message with 16 unique characters, if one characters appears 10K times, while another character appears only once, can we encode it with less bits?

### Solution 2 <arcticons-emoji-electric-light-bulb />

4 bits for each character, $(10K + 15) \times 4 = 40K + 60$ bits in total.

### Solution 3 <arcticons-emoji-electric-light-bulb />

What if we can use only 1 bit for the most frequent character?

- `0`: the most frequent character
- `1` + Another 4 bits: other 15 characters

Then, it takes $10K \times 1 + 15 \times 5 = 10K + 75$ bits.

<v-click>

> This is the basic idea behind Huffman coding: to use fewer bits for characters that occur more frequently.

</v-click>

---

# 2. Huffman Coding

A [Huffman code](https://en.wikipedia.org/wiki/Huffman_coding) is a particular type of optimal prefix code that is commonly used for lossless data compression.

> A prefix code is a type of code system distinguished by its possession of the "prefix property", which requires that there is no whole code word in the system that is a prefix (initial segment) of any other code word in the system.

Given the message: `this is an example of a huffman tree`:

| Character | Frequency | Code  |
| --------- | --------- | ----- |
| space     | 7         | 111   |
| a         | 4         | 010   |
| ...       | ...       | ...   |
| x         | 1         | 10010 |

---

# 3. Towards Huffman Tree

<div class="grid grid-cols-12">
  <div class="col-span-7">

  <img src="/week8/huffman.svg" class="h-full" alt="huffman"/>

  </div>
  <div class="col-span-5">

| Character | Frequency | Code  |
| --------- | --------- | ----- |
| space     | 7         | 111   |
| a         | 4         | 010   |
| e         | 4         | 000   |
| f         | 3         | 1101  |
| ...       | ...       | ...   |
| x         | 1         | 10010 |

  </div>
</div>

By convention, right edges are labeled `1` and left edges are labeled `0`. And our goal is to find the optimal prefix code by constructing a binary tree.

---

## Formal Description

### Given

A set of symbols and their frequencies of occurrence (or weight).

### Find

A binary tree with minimum weighted path length from the root.

<div class="flex justify-center items-center h-300px mt-8px">
    <img src="/week8/huffman.svg"  class="h-full" alt="node"/>
</div>

---

## Algorithm: Plain Text

- Begin with a forest of trees. All trees have just one node, with the weight of the tree equal to the weight of the character in the node. Characters that occur most frequently have the highest weights. Characters that occur least frequently have the smallest weights.
- Repeat this step until there is only one tree:
  - Choose two trees with the smallest weights; call these trees $T_1$ and $T_2$.
  - Create a new tree whose root has a weight equal to the sum of the weights $T_1 + T_2$ and whose left sub-tree is $T_1$ and whose right sub-tree is $T_2$.
- The single tree left after the previous step is an optimal encoding tree (a.k.a., Huffman tree).

---

## Algorithm: Illustration

<div class="flex justify-center items-center h-400px mt-8px">
    <img src="/week8/HuffmanCodeAlg.png"  class="h-full" alt="node"/>
</div>

---

## Exercise <arcticons-exercisetimer />

Given the message `go go gophers`, construct the Huffman tree.

<v-click>

## Naive implementation <arcticons-easy-coder />

<div class="grid grid-cols-12">
  <div class="col-span-5">

```python
class HuffmanNode:
    def __init__(self, char, freq):
        self.char = char
        self.freq = freq
        self.left = None
        self.right = None

def build_frequency_dict(text):
    """Build frequency dictionary."""
```

  </div>
  <div class="col-span-7">

```python
def build_huffman_tree(freq_dict):
    nodes = [HuffmanNode(char, freq)
           for char, freq in freq_dict.items()]
    while len(nodes) > 1:
        nodes.sort(key=lambda x: x.freq)
        left = nodes.pop(0)
        right = nodes.pop(0)

        parent = HuffmanNode(None, left.freq + right.freq)
        parent.left = left
        parent.right = right
        nodes.append(parent)
    return nodes[0] if nodes else None
```

  </div>

</div>

</v-click>
