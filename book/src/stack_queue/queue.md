# Queues
Different from stacks, a **queue** is a collection of objects that are inserted and removed according to the *first-in, first-out (FIFO)* policy. It supports two basic operations:

- `enqueue()`: add a new item
- `dequeue()`: remove an item

It also supports two more operations sometimes: `size()` and `isEmpty()`, returning *number of items in the queue*, and answering *is the queue empty?*, respectively.

> Queue: a line of people, usually standing or in cars, waiting for something (from Cambridge dictionary).

When you hear of *queues*, you shall image a line of people waiting for the service before a counter as illustrated in the following figure[^queue]:

<img src="image/queue.png" width="50%">


As a running example of *queues*, let's consider what happens when you are calling 10086. Suppose there is only one telephone operator, and she is free now:

| Action | Contents in the queue | Who is being served |
| ----- | ------ | -------- |
| Bob is calling | [] | Bob |
| Alice is calling | [Alice] | Bob |
| Jack is calling | [Alice, Jack] | Bob |
| Mike is calling | [Alice, Jack, Mike] | Bob |
| Bob ends his calling | [Jack, Mike] | Alice |
| Mary is calling | [Jack, Mike, Mary] | Alice |
| Alice ends her calling | [Mike, Mary] | Jack |
| Jack ends his calling | [Mary] | Mike | 


---
[^queue] This figure is from *Algorithms, 4th*.