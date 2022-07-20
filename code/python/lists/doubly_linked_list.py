from no_element import NoElement


class DoublyLinkedList:
    """A basic doubly linked list."""

    class Node:
        def __init__(self, item=None, prev=None, nxt=None):
            self.item = item
            self.prev = prev
            self.next = nxt

    def __init__(self):
        self._header = self.Node()
        self._trailer = self.Node(None, self._header, None)  # trailer is preceded by header
        self._header.next = self._trailer  # header is followed by trailer
        self._size = 0

    def size(self):
        return self._size

    def is_empty(self):
        return self._size == 0

    def first(self):
        if self.is_empty():
            return None
        else:
            return self._header.next.item

    def last(self):
        if self.is_empty():
            return None
        else:
            return self._trailer.prev.item

    def _add_between(self, item, predecessor, successor):
        node = self.Node(item, predecessor, successor)
        predecessor.next = node
        successor.prev = node
        self._size += 1

    def add_first(self, item):
        self._add_between(item, self._header, self._header.next)

    def add_last(self, item):
        self._add_between(item, self._trailer.prev, self._trailer)

    def _remove(self, node):
        predecessor = node.prev
        successor = node.next
        predecessor.next = successor
        successor.prev = predecessor
        self._size -= 1

    def remove_first(self):
        if self.is_empty():
            raise NoElement
        self._remove(self._header.next)

    def remove_last(self):
        if self.is_empty():
            raise NoElement
        self._remove(self._trailer.prev)

    def __iter__(self):
        return self.DoublyIterator(self._header.next, self._trailer)

    class DoublyIterator:
        def __init__(self, node, trailer):
            self.walk = node
            self.trailer = trailer

        def __next__(self):
            if self.walk is self.trailer:
                raise StopIteration
            answer = self.walk.item
            self.walk = self.walk.next
            return answer
