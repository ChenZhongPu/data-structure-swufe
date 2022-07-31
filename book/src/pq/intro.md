# Priority Queues
A queue is a collection of objects that are inserted and removed according to the *first-in, first-out* (FIFO) policy. However, in practice, we would like to assign a **priority** to the item so that the one with the highest priority will be processed first. For example, you are likely to have a computer (or a cellphone) that is capable of running several applications at the same time. This effect is typically achieved by assigning a priority to events associated with applications, then always choosing to process next the highest-priority event. For example, most cellphones are likely to process an incoming call with higher priority than a game application.

An appropriate ADT in such an environment is called a **priority queue**[^pq], and it supports two operations:
- *remove the maximum*
- *insert*

Using priority queues is similar to using queues (remove the *oldest*) and stacks (remove the *newest*), but implementing them efficiently is more challenging.

---
[^pq] By default, it is *maximum priority queue*.

