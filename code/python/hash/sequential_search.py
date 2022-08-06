class SequentialSearch:
    """Sequential search based on a linked list."""

    class Node:
        def __init__(self, key, value, nxt):
            self.key = key
            self.value = value
            self.next = nxt

    def __init__(self):
        self._n = 0
        self._first = None

    def size(self):
        return self._n

    def is_empty(self):
        return self._n == 0

    def __getitem__(self, key):
        if key is None:
            raise ValueError
        x = self._first
        while x is not None:
            if x.key == key:
                return x.value
            x = x.next
        return None

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
        x = self._first
        while x is not None:
            if x.key == key:  # update
                x.value = value
                return
            x = x.next
        # insert a new key-value pair
        self._first = self.Node(key, value, self._first)
        self._n += 1

    def __delitem__(self, key):
        if key is None:
            raise ValueError
        x = self._first
        y = None  # the node in front of x
        while x is not None:
            if x.key == key:
                if y is None:  # to delete first
                    self._first = self._first.next
                else:
                    y.next = x.next
                self._n -= 1
                return
            y = x
            x = x.next

    def keys(self):
        x = self._first
        while x is not None:
            yield x.key
            x = x.next

    def items(self):
        x = self._first
        while x is not None:
            yield x.key, x.value
            x = x.next
