# Deques in Java
JDK is shipped with the [Deque](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Deque.html) interface as well as many useful implementing classes, including [ArrayDeque](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayDeque.html), [LinkedList](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/LinkedList.html). In this section, we use `ArrayDeque` as the running example, which is a resizable-array implementation of the `Deque` interface. `Deque` defines methods to access the elements at both ends of the deque.

```java
Deque<String> deque = new ArrayDeque<>();
deque.offerLast("structures");
deque.offerLast("is");
deque.offerFirst("data");
deque.offerLast("fun");
assert Objects.equals(deque.pollFirst(), "data");
assert Objects.equals(deque.peekLast(), "fun");
```

The implementation based on circular arrays by yourself is left as an exercise.

> If you need deques in your project, please choose one of the implementing classes from JDK, instead of designing a new one from the scratch.


## Application: number of recent calls
This application is from [leetcode](https://leetcode.com/problems/number-of-recent-calls/), and readers shall check the question description by themselves.

As for this problem, one feasible algorithm is to use `ArrayDeque`:

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class RecentCounter {
    private final Deque<Integer> data;

    public RecentCounter() {
        data = new ArrayDeque<>();
    }

    public int ping(int t) {
        while (!data.isEmpty() && (t - 3000) > data.peekFirst()) {
            data.pollFirst();
        }
        data.offerLast(t);
        return data.size();
    }
}
```
