---
theme: seriph
title: Data Structures-Week11
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

## Lecture 11: Hash Table

CHEN Zhongpu, Fall 2025

<div class="text-10px">
School of Computing and Artificial Intelligence, SWUFE
</div>
<div class="flex justify-center items-center h-60px mt-8px">
    <img src="/swufefull.svg" class="h-full" alt="swufe logo"/>
</div>

---

# Quiz

1. Is the following tree a max heap?

<div class="flex justify-center items-center">
    <img src="/week11/heap.png" class="h-200px" alt="max heap"/>
</div>

2. Why does the following code not work?

```python
s = {[1, 2, 3]}
d = {[1]: 1}
```

---

## 1. Applications

How does software developers prevent software being tampered? A common way is to use [hash functions](https://code.visualstudio.com/Download):

<div class="flex justify-center items-center">
    <img src="/week11/sha256.png" class="h-200px" alt="vscode"/>
</div>

### Code <logos-visual-studio-code />

```shell
echo -n "Data Structure" | sha256sum
```

```python
import hashlib
def string_to_sha256(text):
    return hashlib.sha256(text.encode()).hexdigest()
text = "Data Structure"
hash_value = string_to_sha256(text)
```

---

## 1.1 From Arrays to Hashing

Given an array, what is time complexity if we can access an element by its index?

<div class="flex justify-center items-center">
    <img src="/week11/array.png" class="h-150px" alt="array"/>
</div>

<v-click>

### View Array as a Dict <logos-amazon-chime />

The index is the `key`, and the element is the `value`. The following is [time complexity](https://wiki.python.org/moin/TimeComplexity) of `dict` operations:

| Operation | Average Case |
| --------- | ------------ |
| `k in d`  | $O(1)$       |
| Get item  | $O(1)$       |

</v-click>

---

## 1.2 Direct-address Table

Suppose each element has distinct key drawn from the universe $U = \{0, 1, \ldots, m-1\}$, where `m` is the size of the table. In this case, an array is also known as a <span class="text-red">direct-address table</span>.

<div class="flex justify-center items-center">
    <img src="/week11/direct-address.png" class="h-320px" alt="direct-address table"/>
</div>

---

### Pitfalls of Direct-address Table <logos-airtable />

<div class="flex justify-center items-center">
    <img src="/week11/direct-address.png" class="h-280px" alt="direct-address table"/>
</div>

- If the universe $U$ is large or infinite, storing a table $T$ may be impractical, or even impossible.
- Keys can only be integers.

### Solution <logos-blueprint />

> Instead using the key as an array index directly,
> we **compute** the array index from the key.

---

## 1.3 Hash Function

### Definition By Wikipedia <logos-xwiki />

<div class="flex justify-center items-center">
    <img src="/week11/hash.svg" class="h-300px" alt="hash function"/>
</div>

> A [hash function](https://en.wikipedia.org/wiki/Hash_function) is any function that can be used to map data of arbitrary size to fixed-size values.

The values returned by a hash function are called <span class="text-red">hash values</span>, <span class="text-red">hash codes</span>, <span class="text-red">digests</span>, or simply hashes.

---

### A Formal Definition <logos-arangodb-icon />

The hash function $h$ maps the universe $U$ of keys into slots of a <span class="text-red">hash table</span> $T[0 \ldots m-1]$:

$$h: U \rightarrow \{0, 1, \cdots, m - 1\}$$

where the size $m$ of the hash table is typically much smaller than $|U|$.

<v-click>

### Example <logos-apache-flink-icon />

A simple, but not particularly good hash function is the <span class="text-red">mod method</span>:

$$h(k) = k\ mod\ m$$

</v-click>

---

### How to Choose a Hash Function? <logos-aurora />

In Python, the built-in `hash()` function can be used to generate hash values. For example:

```python
>>> hash(1)
1
>>> hash(3.14)
322818021289917443
>>> hash('a')
1404876470250145218
>>> hash('ab')
261174798048871521
>>>
```

A good hash function should have the following properties:

- Deterministic: same input, same output.
- Fast to compute.
- Uniform distribution.
- One-way: hard to reverse.

Note that only the first one is the minimum requirement.

---

## 1.4 Collision

A <span class="text-red">collision</span> occurs when two distinct keys $k_1$ and $k_2$ have the same hash value $h(k_1) = h(k_2)$. Clearly, a uniform hash function can reduce the probability of collisions.

<div class="flex justify-center items-center">
    <img src="/week11/collision.png" class="h-300px" alt="collision"/>
</div>

Note that collisions are inevitable when $|U| > m$, so we need to conduct a <span class="red-text">collision resolution</span>.

---

# 2. Collision Resolution: Separate Chaining

Suppose we adopt $h(k) = k\ mod\ m$ as the hash function, where $m = 10$.

> Each non-empty slot points to a linked list of elements that hash to the same slot.

<div class="flex justify-center items-center">
    <img src="/week11/chain.png" class="h-320px" alt="separate chaining"/>
</div>

---

## 2.1 Analysis

Assume that the hash function that we use uniformly and independently distributes keys into $m$ slots. Let $n$ be the number of keys in the table. We can define the <span class="text-red">load factor</span> $\alpha$ as:

$$\alpha = \frac{n}{m}$$

That is to say, the average number of keys per slot.

### Time Complexity <logos-aws-opsworks />

Under the assumption above, a search takes $O(1 + \alpha)$ time on average.

### Re-hashing <logos-builder-io-icon />

What if $\alpha$ is too large? We can re-hash the table to a larger size.

The code can be found in [separate_chaining_hash.py](https://github.com/ChenZhongPu/data-structure-swufe/blob/master/code/python/hash/separate_chaining_hash.py).

---

# 3. Collision Resolution: Open Addressing

If we store all elements in the hash table itself, we use <span class="text-red">open addressing</span> to resolve collisions. In this case the size of the hash table $m$ is no less than the number of keys $n$.

## Probing <logos-aws-cloudformation />

With this method a hash collision is resolved by probing, or searching through to find the next empty slot. A simple probing method is <span class="text-red">linear probing</span>.

Suppose $h(k) = k\ mod\ m$ and $m = 10$.

<div class="flex justify-center items-center">
    <img src="/week11/probing.png" class="h-200px" alt="linear probing"/>
</div>

---

## Linear Probing

<div class="flex justify-center items-center">
    <img src="/week11/probing.png" class="h-200px" alt="linear probing"/>
</div>

It always to find the next empty slot linearly (in which the interval between probes is fixed, often set to 1).

<div class="flex justify-center items-center">
    <img src="/week11/28.png" class="h-120px" alt="linear probing"/>
</div>

Please draw the result after inserting 12, 9, and 10.

---

# Summary

- Hash tables
- Hash functions
- Collision resolution: separate chaining and open addressing
