class LinearProbeHash:
    """A hash symbol table using the linear probing."""

    def __init__(self, cap=4):
        self._n = 0
        self._m = cap
        self._keys = [None] * cap
        self._values = [None] * cap

    def size(self):
        return self._n

    def is_empty(self):
        return self._n == 0

    def _hash(self, k):
        return hash(k) % self._m

    def __setitem__(self, key, value):
        if key is None:
            raise ValueError
        if value is None:
            del self[key]
            return
        if self._n >= self._m // 2:
            self._resize(self._m * 2)
        i = self._hash(key)
        while self._keys[i] is not None:
            if key == self._keys[i]:
                self._values[i] = value
                return  # update
            i = (i + 1) % self._m
        # insert a new key-value pair
        self._keys[i] = key
        self._values[i] = value
        self._n += 1

    def __getitem__(self, key):
        if key is None:
            raise ValueError
        i = self._hash(key)
        while self._keys[i] is not None:
            if key == self._keys[i]:
                return self._values[i]
            i = (i + 1) % self._m
        return None

    def contains(self, key):
        if key is None:
            raise ValueError
        return self[key] is not None

    def __delitem__(self, key):
        if key is None:
            raise ValueError
        if self[key] is None:
            return  # no such key
        # find the position
        i = self._hash(key)
        while self._keys[i] != key:
            i = (i + 1) % self._m
        # delete key and associated value
        self._keys[i] = None
        self._values[i] = None
        # rehash all keys in the same cluster
        i = (i + 1) % self._m
        while self._keys[i] is not None:
            key_to_rehash = self._keys[i]
            value_to_rehash = self._values[i]
            self._keys[i] = None
            self._values[i] = None
            self._n -= 1
            self[key_to_rehash] = value_to_rehash
            i = (i + 1) % self._m
        self._n -= 1
        if 0 < self._n <= self._m // 8:
            self._resize(self._m / 2)

    def _resize(self, cap):
        t = LinearProbeHash(cap)
        for i in range(0, self._m):
            if self._keys[i] is not None:
                t[self._keys[i]] = self._values[i]
        self._m = cap
        self._keys = t._keys
        self._values = t._values
