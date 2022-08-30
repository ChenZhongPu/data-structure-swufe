# Stack In Python
Fortunately, the `list` in Python supports the operations required by stacks. To be specific,

- `append()` is to add a new item at the *end* of the list, so it behaves exactly the same as `push()`.
- `pop`(). Aha! The same method name as we expect.

```python
a = []
a.append(5)
a.append(3)
v = a.pop() # 3
v = a.pop() # 5
a.append(7)
a.append(9)
a.append(6)
v = a.pop() # 6
```

But, we cannot get its size and check the emptiness of a `list` in an object-oriented way. To this end, a better way is to encapsulate the data and related operations in a class. The following is the skeleton of `Stack`, and the complete code can be found at [stack.py](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/python/stack-queue/stack.py). Because the list is an array, we call the following implementation **array-based stack**.

```python
class Stack:
    """A last-in, first-out (LIFO) data structure."""

    def __init__(self):
        self._data = []

    def push(self, item):
        pass

    def pop(self):
        pass

    def size(self):
        pass

    def is_empty(self):
        pass
```

> Python does not support the *private* variables. However, there is a convention that is followed by most Python code: a name prefixed with an underscore (e.g. _spam) should be treated as a non-public part of the API. 

As we can see, `Stack` wraps a list inside, and the implementations of these APIs are generally straightforward as long as you know the basic usage of a list.

- `push()` is based on `append()`:

```python
def push(self, item):
    self._data.append(item)
```

- `size()` is based on built-in `len()`:

```python
def size(self):
    return len(self._data)
```

- `is_empty()` is to check whether its size equals 0:

```python
def is_empty(self):
    return self.size() == 0
```

- `pop()` can use list's `pop()` directly:

```python
def pop(self):
    return self._data.pop()
```

However, if the underlying data is empty, then `pop()` will throw an exception: *IndexError: pop from empty list*. We can further customize the error massage to display something like *Error: pop from empty stack*:

```python
def pop(self):
    if self.is_empty():
        raise IndexError('Pop from empty stack')
    return self._data.pop()
```

Another common approach is to return a `None` when it is empty, but it would be troublesome if the item itself is a `None`:

```python
def pop(self):
    if self.is_empty():
        return None
    return self._data.pop()
```

A third approach is to use a self-defined exception class, and we will adopt this implementation in this book.

```python
class NoElement(Exception):
    """Error attempting to access an element from an empty collection."""
    pass
```

```python
def pop(self):
    if self.is_empty():
        raise NoElement('Pop from empty stack!')
    return self._data.pop()
```

## Iterator
By now you have probably noticed that most container objects can be looped over using a `for` statement:

```python
a = [1, 9, 4, 9]
for i in a:
    print(i)
```

Why can we use `for` to iterate the list? The iterator behind the scenes plays the magic[^for]! See more at [Iterators](https://docs.python.org/3/tutorial/classes.html#iterators).

It is often a good practice to prepare a single class as the iterator which offers the `__next__()` method:

```python
class ReverseListIterator:
    def __init__(self, data):
        self._data = data
        self._i = len(data)

    def __next__(self):
        if self._i == 0:
            raise StopIteration
        self._i -= 1
        return self._data[self._i]
```

And `Stack`'s `__iter__()` method returns an instance of the iterator:

```python
def __iter__(self):
    return ReverseListIterator(self._data)
```

We can also use the built-in `reversed()` iterator:

```python
def __iter__(self):
    return reversed(self._data)
```

In this way, we can iterate the stack as we do for other collections:

```python
s = Stack()
s.push(1)
s.push(2)
s.push(3)
for i in s:
    print(i)
```

## Time complexity
The following table summarizes the array-based stack's time complexity:

| Operation | Running time |
| -------- |  ------- |
| push()  | \\(O(1)\\) |
| pop() | \\(O(1)\\) |
| is_empty() | \\(O(1)\\) |
| size() | \\(O(1)\\) |

As we can see, all APIs are with a constant time complexity. 

Note that the time complexity of `push()` and `pop()` is *amortized*. Roughly speaking, it means \\(O(1)\\) happens most of time, but it may have a larger time complexity only once a while[^resize]; and on average, the worst complexity is still \\(O(1)\\). 

## Application (1): matching parentheses
In this subsection, we explore an application of stacks by considering matching parentheses in arithmetic expressions. Here we assume that only parentheses `()`, braces `{}`, and brackets `[]` are allowed. This application is also found at [LeetCode](https://leetcode.com/problems/valid-parentheses/).

Clearly, each opening symbol must match its corresponding closing symbol. For example:

- Correct: `()(()){([()])}`
- Incorrect: `({[])}`

To what follows, we are going to design an algorithm to check whether an expression is matched in terms of parentheses:

```python
def is_matched(expr):
    """
    Check whether an expression is matched in terms of parentheses

    :param expr: an expression with parentheses
    :return: True if matched; False otherwise
    """
    pass
```

The idea of this algorithm can be described in plain English: 

- Scan the expression for left to right.
- If the character belongs to openings (i.e., `([{`), it is pushed on a stack.
- If the character belongs to the closings (i.e., `)]}`), an item popped from the stack will be checked whether it is matched with the character or not. If so, continue to scan; otherwise, return `False`.
- After scanning, if the stack is empty, return `True`; otherwise, return `False`.

The steps above can be translated into the code:

```python
def is_matched(expr):
    openings = '([{'
    closings = ')]}'
    s = Stack()
    for c in expr:
        if c in openings:
            s.push(c)
        elif c in closings:
            if s.is_empty():
                return False
            if closings.index(c) != openings.index(s.pop()):
                return False
    return s.is_empty()
```

## Application (2): arithmetic expression evaluation

As another example[^arithmetic] of a stack, let's consider the basic arithmetic expression evaluation. For example,

```
(1 + ((2 + 3) * (4 * 5)))
```

If you multiply 4 by 5, add 3 to 2, multiply the result, and then add 1, you get the value 101. How to this calculation using Python?

For simplicity, we begin with the following explicit recursive definition: an *arithmetic expression* is either a number, or a left parenthesis (i.e., `(`) followed by an arithmetic expression followed by an operator followed by another arithmetic expression followed by a right parenthesis (i.e., `)`). In other words, we rely on parentheses instead of precedence rules.

For specificity, we support the familiar binary operators `*`, `+`, `-`, and `/`. A remarkably simple algorithm that was developed by E. W. Dijkstra in the 1960s uses two stacks (**one for operands and one for operators**) to do this job. Again, the idea of this algorithm can be described in plain English when scanning the expression according to four possible cases: 

- Push *operands* onto the operand stack.
- Push *operators* onto the operator stack.
- Ignore *left* parentheses.
- On encountering a *right* parenthesis, pop an operator, pop the operands, and push onto the operand stack the result of applying the operator to those operands.

After the final right parenthesis has been processed, there is one value on the stack, which is the value of the expression. For example, the algorithm computes the same value for all of these expressions:

```
( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) ) 
( 1 + ( 5 * ( 4 * 5 ) ) )
( 1 + ( 5 * 20 ) )
( 1 + 100 )
101
```

For simplicity, the code that the expression is fully parenthesized, with numbers and characters separated by whitespace:

```python
def compute(expr: str) -> float:
    """
    Dijkstra's two-stack algorithm for expression evaluation.

    :param expr: an arithmetic expression
    :return: computed value
    """
    ops = Stack()
    vals = Stack()
    expr = expr.split(' ')
    for c in expr:
        if c == '(':
            continue
        elif c in '+-*/':
            ops.push(c)
        elif c == ')':
            v = vals.pop()
            op = ops.pop()
            if op == '+':
                v = vals.pop() + v
            elif op == '-':
                v = vals.pop() - v
            elif op == '*':
                v = vals.pop() * v
            elif op == '/':
                v = vals.pop() / v
            vals.push(v)
        else:
            vals.push(float(c))
    return vals.pop()
```


---
[^for] It will call `__getitem__()` instead if `__iter__()` is not available.

[^resize] It is because of the resizing of an array.

[^arithmetic] This example comes from *Algorithms, 4th*.