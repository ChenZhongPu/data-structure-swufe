# Algorithm Analysis
How fast is a program? If the (time) efficiency is our major concern, then we have to ask this question regularly.

## A hands-on method
A straightforward way is to measure the elapsing time. In the following, we will use a tiny program ([Fibonacci.java](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/java/start/src/Fobonacci.java) and [fibonacci.py](https://github.com/ChenZhongPu/data-structure-swufe/tree/master/code/python/start/fibonacci.py)) to compute the nth fibonacci number.

### Java
In Java, there are many APIs available in terms of time/date, including `System.currentTimeMillis()`, `Instant.now`. You can use either of the following method[^java-time] to compute the elapsing time in milliseconds:

```java
long start = System.currentTimeMillis();
// your program runs
long end = System.currentTimeMillis();
long elapse = end - start;
```

```java
long start = Instant.now().toEpochMilli();
// your program runs
long end = Instant.now().toEpochMilli();
long elapse = end - start;
```

```java
Instant start = Instant.now();
// your programs runs
Instant end = Instant.now();
long elapse = Duration.between(start, end).toMillis();
```

### Python
In Python, several modules can be used to deal with time/date, including `time`, `datetime`. But note that `time.time()` returns a float number representing the current time in seconds since the Epoch. You can use either of the following code to compute the elapsing time in milliseconds:

```python
start = int(round(time.time() * 1000))
# your program runs
end = int(round(time.time() * 1000))
elapse = end - start
```

```python
start = datetime.datetime.now()
# your program runs
end = datetime.datetime.now()
elapse = int(round((end - start).total_seconds() * 1000))
```



---
[^java-time] See more at https://stackoverflow.com/questions/58705657/.