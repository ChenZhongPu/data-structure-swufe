class Stack:
    """A last-in, first-out (LIFO) data structure."""

    def __init__(self):
        self._data = []
        self._index = 0

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
        self._index = self.size()
        return self

    def __next__(self):
        if self._index == 0:
            raise StopIteration
        self._index -= 1
        return self._data[self._index]
