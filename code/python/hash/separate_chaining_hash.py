from sequential_search import SequentialSearch


class SeparateChainingHash:
    INIT_CAPACITY = 4

    def __init__(self, cap=INIT_CAPACITY):
        self._m = cap
        self._n = 0
        self._table = [None] * cap
        for i in range(0, cap):
            self._table[i] = SequentialSearch()

    def size(self):
        return self._n

    def is_empty(self):
        return self._n == 0

    def _resize(self, cap):
        t = SeparateChainingHash(cap)
        for item in self._table:
            for (k, v) in item.items():
                t[k] = v
        self._m = t._m
        self._table = t._table

    def _hash(self, key):
        return hash(key) % self._m

    def __getitem__(self, key):
        if key is None:
            raise ValueError
        i = self._hash(key)
        return self._table[i][key]

    def contains(self, key):
        if key is None:
            raise ValueError
        return self[key] is not None

    def __setitem__(self, key, value):
        if key is None:
            raise ValueError
        if value is None:
            del self[key]
            return
        if self._n >= 10 * self._m:
            self._resize(2 * self._m)
        i = self._hash(key)
        if not self._table[i].contains(key):
            self._n += 1
        self._table[i][key] = value

    def __delitem__(self, key):
        if key is None:
            raise ValueError
        i = self._hash(key)
        if self._table[i].contains(key):
            self._n -= 1
        del self._table[i][key]

        if self._m > self.INIT_CAPACITY and self._n < 2 * self._m:
            self._resize(self._m // 2)
