from no_element import NoElement


class CircularLinkedList:
    """A circularly linked list."""

    class Node:
        def __init__(self, item, nxt=None):
            self.item = item
            self.next = nxt

    def __init__(self):
        self._tail = None
        self._size = 0

    def size(self):
        return self._size

    def is_empty(self):
        return self._size == 0

    def first(self):
        if self.is_empty():
            return None
        return self._tail.next.item

    def last(self):
        if self.is_empty():
            return None
        return self._tail.item

    def add_first(self, item):
        if self.is_empty():
            self._tail = self.Node(item)
            self._tail.next = self._tail
        else:
            self._tail.next = self.Node(item, self._tail.next)
        self._size += 1

    def add_last(self, item):
        self.add_first(item)
        self._tail = self._tail.next

    def remove_first(self):
        if self.is_empty():
            raise NoElement
        head = self._tail.next
        if head is self._tail:
            # only one element
            self._tail = None
        else:
            self._tail.next = head.next
        self._size -= 1

    def remove_last(self):
        if self.is_empty():
            raise NoElement
        head = self._tail.next
        if head is self._tail:
            # only one element
            self._tail = None
        else:
            # find the second to the last
            while head.next is not self._tail:
                head = head.next
            head.next = self._tail.next
            self._tail = head
        self._size -= 1

    def rotate(self):
        if not self.is_empty():
            self._tail = self._tail.next

    def __iter__(self):
        return self.LinkedIterator(self._tail, self._size)

    class LinkedIterator:
        def __init__(self, tail, size):
            self.i = 0
            self.size = size
            if size > 0:
                self.node = tail.next
            else:
                self.node = None

        def __next__(self):
            if self.i >= self.size:
                raise StopIteration
            answer = self.node.item
            self.node = self.node.next
            self.i += 1
            return answer
