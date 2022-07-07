# The List ADT
In this section, we will formally introduce an important concept: 

> Abstract Data type (ADT) is a type (or class) for objects whose behavior is defined by a set of values and a set of operations.

So, the previous introduced stacks, queues, and deques all are ADTs. You do not need to know how a data type is implemented in order to be able to use it. Note that the definition of ADT only mentions what operations are to be performed but not how these operations will be implemented. For example, a queue based on `ArrayList` and a queue based on circular arrays are two different implementations of the queue ADT.

The list ADT represent an ordered collection containing **linear sequence of elements**, but with more general support for adding or removing elements at arbitrary positions. Since it is linear, locations within a list ADT are easily described with an integer **index**.

With that said, Java defines a general interface, [java.util.List](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html), that includes the following useful methods[^index] (and more):

- `add(e)`: Appends the specified element to the end of this list.
- `add(i, e)`: Inserts the specified element at the specified position in this list.
- `contains(o)`: Returns true if this list contains the specified element.
- `get(i)`: Returns the element at the specified position in this list.
- `indexOf(o)`: Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
- `isEmpty()`: Returns true if this list contains no elements.
- `lastIndexOf​(o)`: Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element.
- `remove(i)`: Removes the element at the specified position in this list.
- `remove(o)`: Removes the first occurrence of the specified element from this list, if it is present.
- `set(i, o)`: Replaces the element at the specified position in this list with the specified element.
- `size()`: Returns the number of elements in this list.

`ArrayList` is a well known implementing class of `List` interface, and you can play with those methods on it.

## Revisit Python's `list`
Now let's revisit the methods provided by Python's `list`, and then compare them with their Java's counterparts.

> Python's `list` is very closely related to the `ArrayList` class in Java.

|  Java's List |  Python's `list` |
| ------------- | --------------- |
| add(e)      |  append(e)    |
| add(i, e)  |  insert(i, e) |
| contains(o) | `in` |
| get(i) | `[i]` |
| indexOf(o) | index(o) |
| isEmpty() | built-in `len()` |
| lastIndexOf​(o) | * |
| remove(i) | pop(i) |
| remove(o) | remove(o) |
| set(i, o) | `[i]` |
| size() | built-in `len()` |

The implementation marked with * is left as an exercise.

---
[^index] Most of them are index-based.