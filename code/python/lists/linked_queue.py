from no_element import NoElement


class Node:
    def __init__(self, item, nxt=None):
        self.item = item
        self.next = nxt


class LinkedQueue:
    """A queue based on singly linked lists."""

    def __init__(self):
        self._head = None
        self._tail = None
        self._size = 0

    def size(self):
        return self._size

    def is_empty(self):
        return self._head is None

    def peek(self):
        if self.is_empty():
            raise NoElement('Peek from an empty queue')
        return self._head.item

    # enqueue() = add_last()
    def enqueue(self, item):
        old_tail = self._tail
        self._tail = Node(item)
        if self.is_empty():
            self._head = self._tail
        else:
            old_tail.next = self._tail
        self._size += 1

    # dequeue() = remove_first()
    def dequeue(self):
        if self.is_empty():
            raise NoElement('Dequeue from an empty queue')
        answer = self._head.item
        self._head = self._head.next
        if self.is_empty():
            self._tail = None
        self._size -= 1
        return answer

    def __iter__(self):
        return LinkedIterator(self._head)


class LinkedIterator:
    def __init__(self, head: Node):
        self.walk = head

    def __next__(self):
        if self.walk is None:
            raise StopIteration
        answer = self.walk.item
        self.walk = self.walk.next
        return answer
