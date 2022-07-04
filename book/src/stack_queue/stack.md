# Stacks
A **stack** is a collection of objects that are inserted and removed according to the *last-in, first-out (LIFO)* policy. It supports two basic operations:

- `push()`: add a new item
- `pop()`: remove an item

It also supports two more operations sometimes: `size()` and `isEmpty()`, returning *number of items in the stack*, and answering *is the stack empty?*, respectively.

> Stack: a pile of things arranged one on top of another (from Cambridge dictionary).

When you hear of *stacks*, you shall image a stack of documents (or plates) as illustrated in the following figure[^stack]:

<img src="image/stack.png" width="40%">

The following table shows a series of stack operations and their effects on an initially empty stack `S` of integers:

| Operation | Stack contents |
| ----------| ------------ |
| S.push(5) |  [5] |
| S.push(3) |  [5, 3] |
| S.pop()   |  [5] |
| S.pop()   |  []  |
| S.push(7) |  [7] |
| S.push[9] |  [7, 9] |
| S.push(6) | [7, 9, 6] |
| S.pop()   | [7, 9] |

The importance of stacks in computing is profound. For example, the operating system would maintain a stack for variables and states when executing programs, but the detailed discussion of this topic is out of the scope of this book. Here let's consider a common example of a stack when surfing the web. 

```html
<!-- a.html -->
<a href="b.html">Next</a>
```

```html
<!-- b.html -->
<a href="c.html">Next</a>
```

```html
<!-- c.html -->
<a href="d.html">Next</a>
```

When you click a hyperlink, your browser displays the new page (and pushes on a stack). You can keep clicking on hyperlinks to visit new pages, but you can always revisit the previous page by clicking the back button (popping it from the stack). The LIFO policy offered by a stack provides just the behavior that you expect.

| Action |  Contents in the stack |  Where you are |
| ---- |  ------- | ------ | 
| (Start from a.html) | [] | a.html |
| Click `Next` | [a.html] | b.html |
| Click `Next` | [a.html, b.html] | c.html |
| Click `Next` | [a.html, b.html, c.html] | d.html |
| Click `Back` | [a.html, b.html] | c.html |
| Click `Back` | [a.html] | b.html |


---
[^stack] This figure is from *Algorithms, 4th*.