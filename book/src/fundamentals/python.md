# Python Built-in Data Structures
In this section, we will introduce three essential data structures in Python. You can find more in [Data Structures](https://docs.python.org/3/tutorial/datastructures.html) from Python's official tutorial and [Common Python Data Structures ](https://realpython.com/python-data-structures/).

In addition, a quick overview about the *type hinting* in Python will also be covered.

## List
Python's list assembles Java's `ArrayList`, representing a resizable array of items.

```python
num = [1, 9, 4, 2]
```

Since Python is a weak-type language, you can even put items with different types in a list:

```python
num = ['1', 9, 'four', 2]
```

But it is not recommended. If you need to store items with mixing types, you can resort to a `tuple`:

```python
# t = 'one', 9
t = ('one', 9)
```

Python is featured by its indexing and slicing. For example,

```python
print(num[0])
print(num[0:2])
print(num[:])
print(num[-1])
print(num[2:-1])
```

As for the iteration, Python's `for` is convenient:

```python
for i in a:
    print(i)
```

Sometimes, we would like the index as well as its value while iterating a list:

```python
for i, v in enumerate(a):
    print(f"Index of {i} is {v}")
```

You are required to know and practice at least those methods:

- `append()`
- `extend()`
- `insert()`
- `remove()`
- `pop()`
- `clear()`
- `sort()`
- `reverse()`

Another nice feature in Python is the list comprehension, providing a concise way to create lists:

```python
squares = [x**2 for x in range(10)]
```

By taking the leveraging of Java's `Stream` API, we can achieve a similar but wordy implementation[^list]:

```java
List<Integer> squares = IntStream.range(0, 10).map(x -> x * 2).boxed().collect(Collectors.toList());
```

Wow! Python saves a lot of keystrokes.

Note that in Java we use the `size()` method to get the size of an `ArrayList`, but the `len()` method in Python is not defined on a list itself. So you will see the following error if `a.len()` is used:

> Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
AttributeError: 'list' object has no attribute 'len'

Instead, you should use `len(a)` to get the size of list `a`.

To test whether an item is in a list or not, we can use `in` (the opposite is `not in`) operator, while `contains()` method is used in Java:

```
num = [1, 9, 4, 2]
print(1 in num) # True
print(3 in num) # False
print(3 not in num) # True
```

## Set
Python's set assembles Java's `HashSet`, representing an unordered collection of items without duplication. Note that to create an empty set you have to use `set()`, not `{}`.

```python
words = {'hello', 'world', 'hello'}
print(words) 
print(len(words))
```

The results will show that duplicates have been removed. By the way, the weak-type of Python makes it also possible to put mixing types in a set. Again, it is not recommended.

The key fact is that there is no order defined in a set, which is essentially `set` used in mathematics. For example, `{0, 1, 2}` and `{1, 0, 2}` are equivalent. 

```python
a = {0, 1, 2}
b = {1, 0, 2}
print(a == b) # True
```

In addition, we can also conclude that indexing in a set is meaningless.

You are required to know and practice at least those methods:

- `add()`
- `clear()`
- `discard()`
- `pop()`
- `remove()`
- `union()`
- `update()`

Like the list, iterations via `for`, `len()`, `in` (`not in`), and set comprehensions are also supported. 

## Dictionary
Python's `dict` assembles Java's `HashMap`, representing a collection of key-value pairs. Dictionaries can be used to represent a mapping between a key and a value, and we assume that keys are unique. For example:

| Key | Value |
|---------|---------|
| Name     | Age     |
| ISBN | Price |
| City | Country |
| Country | GDP |

Different from the list, a dict should manage a collection of pairs. Let's consider a shopping cart which maintains pairs of ISBN (key) and amount (value).

```python
cart = {'7801': 3, '9902': 1}
```

To add another item into this cart:

```python
cart['8809'] = 1
```

To remove an item by its key:

```python
del cart['7801']
```

To get the value via a key:

```python
v = cart['9902']
v = cart.get('9902')
v = cart.get('9902', 0)
```

To some extent, we can imagine a list as a map whose key is implicitly the index. Readers can explore the differences between indexing syntax and `get()` to obtain a value.

You are required to know and practice at least those methods:

- `clear()`
- `get()`
- `items()`
- `keys()`
- `pop()`
- `update()`
- `values()`

We can use `in` (`not in`) to test whether a key exists, and `len()` and dict comprehensions are also supported.

```python
print('0011' in cart) # False
print('9902' in cart) # True
```

The syntax to iterate a dict is slightly different:

```python
for k, v in cart.items():
    print(k, v)
```

## Collections
The [collections](https://docs.python.org/3/library/collections.html) module implements specialized container data types providing alternatives to Python’s general purpose built-in containers (i.e., *list*, *tuple*, *set*, and *dict*). In the following, I will introduce `defaultdict` which can supply missing values based on `dict`.

```python
from collections import defaultdict
cart = defaultdict(int)
print(cart['8899'])
cart['3344'] += 3
print(cart['3344'])
```

Readers can compare its usage with the regular `dict`.

## Type hinting
Python is featured for its *dynamic typing* and [duck typing](https://realpython.com/lessons/duck-typing/).

> If it walks like a duck and it quacks like a duck, then it must be a duck.

But sometimes for large projects, the code will become more maintainable if the typing is specified, and the [typing](https://docs.python.org/3/library/typing.html) module make it possible.

```python
def say_hello(name: str) -> str:
    return f'Hello {name}'
```

This wordy version is more readable by providing the types of the parameter and returned value, and programmers who use *static typing* languages (e.g., Java) would feel more conformable. The following code is implemented with Java:

```java
public String sayHello(String name) {
    return String.format("Hello %s", name);
}
```

It is worthy to note that the Python runtime does not enforce function and variable type annotations. So it is still okay to pass a non-str value (e.g., an integer) to `say_hello()`:

```python
hi = say_hello(42)
```

In contrast, your code won't be complied if you use `sayHello(42)` in Java.

Since Python is a *duck typing* language, it will check for the presence of a given method or attribute. For example,

```python
def say_length(name: str) -> str:
    return f'Hello {len(name)}'
```

In this case, Python will expect that `len()` can be applied on the parameter. So `say_length(42)` will throw a *TypeError*, while the following code works well:

```python
say_length('data')
say_length([1, 9, 4, 9])
say_length({'S', 'W', 'E', 'N'})
```

*Type hinting* is very helpful for large projects, but for simplicity, I will not use this feature for most cases throughout this book.

---
[^list] Java 16 brought a shorter `toList()` method.