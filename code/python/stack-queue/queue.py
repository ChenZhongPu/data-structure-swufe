from no_element import NoElement


class Queue:
    """A first-in, first-out (FIFO) data structure."""

    def __init__(self):
        self._data = []

    def size(self):
        return len(self._data)

    def is_empty(self):
        return len(self._data) == 0

    def enqueue(self, item):
        self._data.append(item)

    def dequeue(self):
        if self.is_empty():
            raise NoElement('Dequeue from empty queue!')
        return self._data.pop(0)

    def __iter__(self):
        return iter(self._data)

