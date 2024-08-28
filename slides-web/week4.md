---
theme: seriph
title: Data Structures-Week4
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

## Lecture 4: Queue and Deque

CHEN Zhongpu, Fall 2024

<div class="text-10px">
School of Computing and Artificial Intelligence, SWUFE
</div>
<div class="flex justify-center items-center h-60px mt-8px">
    <img src="/swufefull.svg" class="h-full" alt="swufe logo"/>
</div>

---

# A Small Quiz

1. What does it print?

```python
s = Stack()
s.push(1)
s.push(9)
s.push(8)
print(s.pop())
```

2. Suppose an initially empty stack `S` has executed a total of 25 `push` operations, 12 `top` operations, and 10 `pop` operations, 3 of which raised `Empty` errors that were caught and ignored. What is the current size of `S`? (R-6.2)
