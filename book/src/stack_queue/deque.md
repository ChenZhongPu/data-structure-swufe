# Deques
In this section, we consider a queue-like data structure that supports insertion and deletion at both the front and the back of the queue. Such a structure is called a **double-ended queue**, or **deque**, which is usually pronounced *deck* to avoid confusion.

The deque collection is more general than both the stack and queue. The extra generality makes it appealing in many applications. Let's consider the analogy that people are calling 10086, and it is often described as a queue. But in reality, people who are at the end of the queue may become impatient and leave the queue (`delete_last()`). Besides, someone who is a VIP can jump the queue (`add_first()`) without long time waiting.

A deque supports the following methods:

- `add_first()`: Add a new element to the front of a deque
- `add_last()`: Add a new element to the end of a deque
- `delete_first()`: Remove and return the first element from a deque
- `delete_last()`: Remove and return the last element from a deque

Additionally, it will include the following accessors:

- `first()`: Return (but not to remove) the first element of a deque
- `last()`: Return (but not to remove) the last element of a deque 
- `is_empty()`: Return `True` if a deque does not contain any element
- `size()`: Return the number of elements in a deque

