# Exercise
1. As for stacks, another operation `top()` is often used. It works like `pop()`, but it will keep the item in the stack. Please implement `top()` method in Java or Python.

---
2. In [stack.py](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/python/stack-queue/stack.py), we would often expect that `len()` can be used directly like other collections in Python:

```python
s = Stack()
print(len(s))
```

Please make it possible for `Stack`.

*Hint: you can refer to [Python `__len__()` Magic Method](https://blog.finxter.com/python-__len__-magic-method/).*

---
2. In [evaluate.py](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/python/stack-queue/evaluate.py),  `if` statement is used to determine the evaluation logic:

```python
if op == '+':
    v = vals.pop() + v
elif op == '-':
    v = vals.pop() - v
elif op == '*':
    v = vals.pop() * v
elif op == '/':
    v = vals.pop() / v
```

Please try to refactor via [operator](https://docs.python.org/3/library/operator.html) module to simplify the code. 

*Hint: you can refer to [Turn string into operator](https://stackoverflow.com/questions/1740726/).*

---
3. The current implementation for Dijkstra's two-stack algorithm for expression evaluation ([evaluate.py](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/python/stack-queue/evaluate.py), [Evaluate.java]()) only support `+`, `-`, `*`, and `/`. Please add the `sqrt` operator that takes just one argument.

```python
v = evaluate.compute('( ( 1 + sqrt ( 5.0 ) ) / 2.0 )')
print(v)
```