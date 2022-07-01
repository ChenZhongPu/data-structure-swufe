# Test Driven Development
Correctness in our programs is the extent to which our code does what we intend it to do. So, how can we guarantee that our code is correct?

Let's consider that Fibonacci sequence program again. Most beginners would adopt the traditional *print-check* method manually.

```python
print(fibonacci(0)) # expect: 0
print(fibonacci(1)) # expect: 1
print(fibonacci(2)) # expect: 1
print(fibonacci(3)) # expect: 2
print(fibonacci(4)) # expect: 3
print(fibonacci(5)) # expect: 5
...
```

But this method is somewhat in a low-level. In this section, I will introduce an automated approach.

## Assert
An assertion allows testing the correctness of any assumptions that have been made in the program, and once an `assert` fails, the program will crash. So, assertions can be used for testing. See more at [Assertions in Java](https://www.geeksforgeeks.org/assertions-in-java/) and [Python's assert](https://realpython.com/python-assert-statement/).

### Java

```java
Fibonacci f = new Fibonacci();
assert f.fibonacci(5) == 5;
```

And you can even provide additional message for an assertion: 

```java
assert f.fibonacci(5) == 5 : "Error when n is 5";
```

### Python

```python
assert fibonacci(5) == 5
```

And you can even provide additional message for an assertion: 

```python
assert fibonacci(5) == 5, 'Error when n is 5'
```

## Unit tests

> Testing is a complex skill: we can’t cover every detail about how to write good tests.

For modern software engineering, the test driven development (TDD) is widely adopted. To put it simply, the key point of TDD includes:

- **Write tests before real implementations**. It may sound ridiculous, but it is feasible as long as you can determine the *input* and expected *output* of a program. As for the Fibonacci sequence, we can expect that `fibonacci(5)` equals 5 before implementing the code.
- **Write tests for every public API and isolate tests from core code**. 