class Stack:
    """A last-in, first-out (LIFO) data structure."""

    def __init__(self):
        self._data = []

    def push(self, item):
        self._data.append(item)

    def pop(self):
        if self.is_empty():
            return None
        return self._data.pop()

    def size(self):
        return len(self._data)

    def is_empty(self):
        return self.size() == 0

    def __iter__(self):
        return ReverseListIterator(self._data)


class ReverseListIterator:
    """
    An iterator for a stack by reversing a list
    """

    def __init__(self, data):
        self._data = data
        self._i = len(data)

    def __next__(self):
        if self._i == 0:
            raise StopIteration
        self._i -= 1
        return self._data[self._i]
