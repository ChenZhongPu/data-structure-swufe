# Hash in Python
Python provides in built-in function `hash()` to generate a hash code. As for your own class, you should implement the `__hash__()` method. An easy, correct way to implement it is to use a key tuple. 

```python
class Book:
    def __init__(self, name, price):
        self._name = name
        self._price = price

    def __hash__(self):
        return hash((self._name, self._price))
```

Like Java, an important rule to obey is that if a class defines equivalence through `__eq__`, then any implementation of `__hash__` must be consistent, in that if `x == y`, then `hash(x) == hash(y)`. 

## A good hash function
In summary, we have three primary requirements in implementing a good hash function for a given data type:

- It should be **consistent**: equal keys must produce the same hash value
- It should be **efficient** to compute
- It should **uniformly distribute the keys**

Satisfying these requirements simultaneously is a job for experts. Python Programmers can assume `hash()` does the job.