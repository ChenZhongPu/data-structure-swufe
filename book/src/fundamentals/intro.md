# Introduction
Let's continue to use the analogy of being a librarian, so you have to design an efficient way to manage the large amount of books. Suppose we need only to care about the names of books, we can use an array in Java or a list in Python:

```java
String[] books = {"Gone with the Wind", "Hands on Data Structures"};
```

```python
books = ["Gone with the Wind", "Hands on Data Structures"]
```

But, **the capabilities of different data structures vary considerably**. For example, an *array* in most programming languages is in a fixed size. To put another way, you cannot add or remove any item in it, and this feature is ridiculous for a general library management system. 

Given the pitfall of an array, can we always prefer to a list? The answer is NO! Because trade-offs are ubiquitous in computer science, different data structures have their own pros and cons, and we should choose an appropriate data structure by considering the specific context. As for an array, it is best due to its efficiency if we have already known that the size of items won't be changed.

By the way, if we would like the feature of adding/removing in Java, we can use `ArrayList` in Java alternatively:

```java
List<String> books = new ArrayList<>();
books.add("Gone with the Wind");
books.add("Hands on Data Structures");
```

Now let's have a look at the term "efficiency", which is the motivation of designing new data structures and algorithms. Generally speaking, we mainly care about the two kinds of *efficiency*:

- Time: A program that costs less time is considered as a better one.
- Space: A program that costs less memory/disk[^space] is considered as a better one.

Here is an example about time efficiency. If you are asked to find the book titled "Gone with the Wind", how do you do? You probably would check each item *one by one* in the list (or array). On the *bast* case that you are lucky enough, the desired book is the first one, so the checking operation is done in only one time. But on the *worst* case, the desired one is the last, so the checking operation is done in N times, where N is the number of the books. And on *average* case, this operation is expected to be done in N/2 times. As we can see, this basic can be time-consuming sometimes. 

A natural question is: can we find a data structure that is capable of finding a desired book in only one time in all cases? The answer is YES, but please keep in mind that there might be some trade-offs. In other words, a new data structure with better time efficiency may be shipped with other pitfalls. Therefore, **it is the programmer's responsibility to choose an appropriate data structure as well as algorithm according to difference contexts**. On the other hand, we are always aiming to find good data structures and algorithms which are efficient in most cases practically and theoretically.

Last but not the least, for most programmers, they don't really have to design innovative data structures or algorithms, because most the languages and third packages/libraries have provided a large number of on-shelf data structures and algorithms. Then why do we still need to learn this course? There are two-fold reasons:

- Understanding how a data structure and algorithm work behind the scenes is necessary when choosing an appropriate one.
- You should be a *creator*, not just a *user*. Sometimes, there is no handy data structures or algorithms available, so you have to design a new one.


---
[^space] In this book, we mainly discuss about the memory overhead of an data structure or algorithm.