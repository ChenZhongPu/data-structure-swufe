# Exercise

1. In [CoffeeDB.java](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/java/start/CoffeeDB.java), we maintain a collection of coffees in an `ArrayList`, and this class offers a simple search method:

```java
public List<Coffee> findByName(String name) {
    ...
}
```

Your task is to complete its Python version below in [coffee_db.py](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/python/start/coffee_db.py):

```python
def find_by_name(self, name):
    pass
```


---
2. `HashMap` and `dict`.
- What would you get if the key does not exist for `HashMap` in Java?
- What is the difference between indexing syntax and `get()` to obtain a value for `dict` in Python?

---
3. Please add a method to delete books by the names from a shop cart in [ShopCart.java](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/java/start/src/ShopCart.java) or [shop_cart.py](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/python/start/shop_cart.py). (*Hint: it does not make sense to store an item when its amount is less or equal 0.*)


---
4. Please write unit tests for [naive_two_sum.py](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/python/start/naive_two_sum.py). (*Hint: you can refer to the Java implementation [NaiveTwoSumTest.java](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/java/unit-work/src/test/java/org/swufe/datastructure/NaiveTwoSumTest.java).*)

---
5. Please give a big O characterization in terms of `n` for each function shown in [example.py](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/python/start/example.py).

---
6. Perform experimental analysis to test the hypothesis that Java's `sort()` or Python's `sorted()` method runs in \\(O(n\log{n})\\) on average.

```java
// a is a random list
List<Integer> a = Arrays.asList(1, 9, 4, 6);
Collections.sort(a);
```

```python
# a is a random list
a = [1, 9, 6, 4]
a = sorted(a)
```

---
7. Please give a big O characterization in terms of `n` for [BinarySearch.java](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/java/unit-work/src/main/java/org/swufe/datastructure/BinarySearch.java) or [binary_search.py](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/python/start/binary_search.py).