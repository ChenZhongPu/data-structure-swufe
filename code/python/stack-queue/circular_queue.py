from no_element import NoElement


class CircularQueue:
    """A queue based on a circular array."""

    DEFAULT_CAPACITY = 10

    def __init__(self):
        self._data = [None] * CircularQueue.DEFAULT_CAPACITY
        self._size = 0
        self._front = 0

    def size(self):
        return self._size

    def is_empty(self):
        return self.size() == 0

    def enqueue(self, item):
        # expand
        if self.size() == len(self._data):
            self._resize(2 * len(self._data))
        avail = (self._front + self.size()) % len(self._data)
        self._data[avail] = item
        self._size += 1

    def dequeue(self):
        if self.is_empty():
            raise NoElement('Dequeue from empty queue!')
        # shrink (optional)
        if self.size() <= len(self._data) // 4:
            self._resize(len(self._data) // 2)
        answer = self._data[self._front]
        self._data[self._front] = None
        self._front = (self._front + 1) % len(self._data)
        self._size -= 1
        return answer

    def _resize(self, capacity):
        assert capacity > self.size()
        old = self._data
        self._data = [None] * capacity
        walk = self._front
        for i in range(self._size):
            self._data[i] = old[walk]
            walk = (1 + walk) % len(old)
        self._front = 0

    def __iter__(self):
        return CircularIterator(self._data, self._front, self._size)


class CircularIterator:
    def __init__(self, data, front, size):
        self._data = data
        self._front = front
        self._size = size
        self._i = 0

    def __next__(self):
        if self._i >= self._size:
            raise StopIteration
        index = (self._front + self._i) % len(self._data)
        self._i += 1
        return self._data[index]
