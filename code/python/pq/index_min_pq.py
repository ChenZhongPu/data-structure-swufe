from no_element import NoElement


class IndexMinPQ:
    """Index minimum priority queue."""

    def __init__(self, capacity):
        self._max_n = capacity
        self._n = 0
        self._keys = [None] * (self._max_n + 1)
        self._pq = [0] * (self._max_n + 1)
        self._reverse_pq = [-1] * (self._max_n + 1)

    def size(self):
        return self._n

    def is_empty(self):
        return self._n == 0

    def contains(self, i):
        return self._reverse_pq[i] != -1

    def insert(self, i, key):
        self._validate_index(i)
        if self.contains(i):
            raise ValueError
        self._n += 1
        self._keys[i] = key
        self._pq[self._n] = i
        self._reverse_pq[i] = self._n
        self._swim(self._n)

    def min_index(self):
        if self.is_empty():
            raise NoElement
        return self._pq[1]

    def min_key(self):
        if self.is_empty():
            raise NoElement
        return self._keys[self._pq[1]]

    def del_min(self):
        if self.is_empty():
            raise NoElement
        root = self._pq[1]
        self._swap(1, self._n)
        self._n -= 1
        self._sink(1)
        self._reverse_pq[root] = -1
        self._keys[root] = None
        self._pq[self._n + 1] = -1
        return root

    def key_of(self, i):
        self._validate_index(i)
        if not self.contains(i):
            raise NoElement
        return self._keys[i]

    def change_key(self, i, key):
        self._validate_index(i)
        if not self.contains(i):
            raise ValueError
        self._keys[i] = key
        self._swim(self._reverse_pq[i])
        self._sink(self._reverse_pq[i])

    def _validate_index(self, i):
        if i < 0 or i >= self._max_n:
            raise ValueError

    def _swim(self, k):
        while k > 1 and self._greater(k // 2, k):
            self._swap(k // 2, k)
            k //= 2

    def _sink(self, k):
        while 2 * k <= self._n:
            j = 2 * k
            if j < self._n and self._greater(j, j + 1):
                j += 1
            if not self._greater(k, j):
                break
            self._swap(k, j)
            k = j

    def _greater(self, i, j):
        return self._keys[self._pq[i]] > self._keys[self._pq[j]]

    def _swap(self, i, j):
        self._pq[i], self._pq[j] = self._pq[j], self._pq[i]
        self._reverse_pq[self._pq[i]] = i
        self._reverse_pq[self._pq[j]] = j
