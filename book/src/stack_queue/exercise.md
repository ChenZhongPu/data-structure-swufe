# Exercise
1. As for stacks, another operation `top()` (also known as `peek()`) is often used. It works like `pop()`, but it will keep the item in the stack. Please implement `peek()` method in Java or Python.

---
2. In [stack.py](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/python/stack-queue/stack.py), we would often expect that `len()` can be used directly like other collections in Python:

```python
s = Stack()
print(len(s))
```

Please make it possible for `Stack`.

*Hint: you can refer to [Python `__len__()` Magic Method](https://blog.finxter.com/python-__len__-magic-method/).*

---
3. In [evaluate.py](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/python/stack-queue/evaluate.py),  `if` statement is used to determine the evaluation logic:

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
4. The current implementation for Dijkstra's two-stack algorithm for expression evaluation ([evaluate.py](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/python/stack-queue/evaluate.py), [Evaluate.java]()) only support `+`, `-`, `*`, and `/`. Please add the `sqrt` operator that takes just one argument.

```python
v = evaluate.compute('( ( 1 + sqrt ( 5.0 ) ) / 2.0 )')
print(v)
```

---
5. Please implement matching parentheses and arithmetic expression evaluation based on [ArrayListStack.java](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/java/stack-queue/src/main/java/org/swufe/datastructure/ArrayListStack.java) using Java respectively.


---
6. The theoretical analysis shows that the time complexity of `dequeue()` of circular queues is \\(O(1)\\). Please design experiments and visualize your results to validate this theoretical cost based on [circular_queue.py](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/python/stack-queue/circular_queue.py).

---
7. The built-in `id()` function returns the memory address, and  You can also check if two variables are pointing to the same object using `is` operator. Try to use those two methods to validate `a` and `b` point to the same object:

```python
a = [1, 2, 3]
b = a
```

---
8. Please implement a queue based on `ArrayList` in Java.

---
9. As for queues, another operation `first()` (also known as `peek()`) is often used. It works like `dequeue()`, but it will keep the item in the queue. Please implement `peek()` method in Java or Python based on circular queues.


---
10. Please implement a deque based on circular arrays in Java.


---
11. Try to solve problems tagged with [stack]((https://leetcode.com/tag/stack/) ) or [queue](https://leetcode.com/tag/queue/) in LeetCode.

---
12. Postfix notation is an unambiguous way of writing an arithmetic expression without parentheses. It is defined so that if "(exp1)op(exp2)" is a normal fully parenthesized expression whose operation is `op`, the postfix version of this is "exp1 exp2 op". 

So, for example, the postfix version of "((5 + 2) * (8 − 3))/4" is "5 2 + 8 3 − * 4 /". Describe an algorithm to evaluate an expression in postfix notation. For simplicity, the numbers and operators are separated with a space.