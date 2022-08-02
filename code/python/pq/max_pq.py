from no_element import NoElement


class MaxPQ:
    """A max binary heap."""

    def __init__(self, data=None):
        self._pq = []
        self._pq.append(None)  # append a dummy key at index 0
        if data is not None:
            self._pq.extend(data)
            n = len(data)
            for k in range(n // 2, 0, -1):
                self._sink(k)
            assert self._is_max_heap()

    def size(self):
        return len(self._pq) - 1

    def is_empty(self):
        return self.size() == 0

    def _swap(self, i, j):
        self._pq[i], self._pq[j] = self._pq[j], self._pq[i]

    def _less(self, i, j):
        return self._pq[i] < self._pq[j]

    def _swim(self, k):
        while k > 1 and self._less(k//2, k):
            self._swap(k//2, k)
            k //= 2

    def _sink(self, k):
        while 2 * k <= self.size():
            j = 2 * k
            if j < self.size() and self._less(j, j + 1):
                j += 1
            if not self._less(k, j):
                break
            self._swap(k, j)
            k = j

    def insert(self, key):
        self._pq.append(key)
        self._swim(self.size())
        assert self._is_max_heap()

    def max(self):
        if self.is_empty():
            raise NoElement
        return self._pq[1]

    def del_max(self):
        if self.is_empty():
            raise NoElement
        root = self._pq[1]
        self._swap(1, self.size())
        self._pq.pop()
        self._sink(1)
        assert self._is_max_heap()
        return root

    def _is_max_heap_ordered(self, k):
        if k > self.size():
            return True
        left = 2 * k
        right = 2 * k + 1
        if left <= self.size() and self._less(k, left):
            return False
        if right <= self.size() and self._less(k, right):
            return False
        return self._is_max_heap_ordered(left) and self._is_max_heap_ordered(right)

    def _is_max_heap(self):
        if self._pq[0] is not None:
            return False
        return self._is_max_heap_ordered(1)
