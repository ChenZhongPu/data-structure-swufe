from no_element import NoElement


class ArrayDeque:
    """A double-ended queue (deque) based on circular queues."""

    DEFAULT_CAPACITY = 10

    def __init__(self):
        self._data = [None] * ArrayDeque.DEFAULT_CAPACITY
        self._size = 0
        self._front = 0

    def size(self):
        return self._size

    def is_empty(self):
        return self.size() == 0

    def first(self):
        if self.is_empty():
            raise NoElement('Get from empty deque!')
        return self._data[self._front]

    def last(self):
        if self.is_empty():
            raise NoElement('Get from empty deque!')
        back = (self._front + self._size - 1) % len(self._data)
        return self._data[back]

    def add_first(self, item):
        if self.size() == len(self._data):
            self._resize(2 * len(self._data))
        self._front = (self._front - 1) % len(self._data)
        self._data[self._front] = item
        self._size += 1

    def add_last(self, item):
        if self.size() == len(self._data):
            self._resize(2 * len(self._data))
        avail = (self._front + self._size) % len(self._data)
        self._data[avail] = item
        self._size += 1

    def delete_first(self):
        if self.is_empty():
            raise NoElement('Remove from empty queue!')
        if self.size() == len(self._data) // 4:
            self._resize(len(self._data) // 2)
        answer = self._data[self._front]
        self._data[self._front] = None
        self._front = (self._front + 1) % len(self._data)
        self._size -= 1
        return answer

    def delete_last(self):
        if self.is_empty():
            raise NoElement('Remove from empty queue!')
        if self.size() == len(self._data) // 4:
            self._resize(len(self._data) // 2)
        back = (self._front + self._size - 1) % len(self._data)
        answer = self._data[back]
        self._data[back] = None
        self._size -= 1
        return answer

    def _resize(self, capacity):
        assert capacity >= self._size
        old = self._data
        self._data = [None] * capacity
        walk = self._front
        for i in range(self._size):
            self._data[i] = old[walk]
            walk = (1 + walk) % len(old)
        self._front = 0
