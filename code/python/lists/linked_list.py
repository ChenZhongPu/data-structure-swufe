class Node:
    def __init__(self, item, nxt=None):
        self.item = item
        self.next = nxt


class LinkedList:
    """A singly linked list."""

    def __init__(self):
        self._head = None
        self._tail = None
        self._size = 0

    def size(self):
        return self._size

    def is_empty(self):
        return self.size() == 0

    def add_first(self, item):
        node = Node(item)
        node.next = self._head
        self._head = node
        if self._tail is None:
            self._tail = node
        self._size += 1

    def add_last(self, item):
        old_tail = self._tail
        self._tail = Node(item)
        if old_tail is None:
            self._head = self._tail
        else:
            old_tail.next = self._tail
        self._size += 1

    def remove_first(self):
        if self.is_empty():
            raise IndexError('Remove from empty linked list')
        else:
            self._head = self._head.next
            if self._head is None:
                self._tail = None
            self._size -= 1

    def remove_last(self):
        if self.is_empty():
            raise IndexError('Remove from empty linked list')
        else:
            if self._head is self._tail:
                # only one element
                self._head = None
                self._tail = None
            else:
                walk = self._head
                while walk.next is not self._tail:
                    walk = walk.next
                walk.next = None
                self._tail = walk
            self._size -= 1

    def index_of(self, item):
        idx = -1
        walk = self._head
        while walk is not None:
            idx += 1
            if walk.item == item:
                return idx
            else:
                walk = walk.next
        return -1

    def _get_node(self, i) -> Node:
        if i < 0 or i >= self._size:
            raise IndexError('Out of bounds')
        cnt = 0
        walk = self._head
        while cnt < i:
            walk = walk.next
            cnt += 1
        return walk

    def get(self, i):
        walk = self._get_node(i)
        assert walk is not None
        return walk.item

    def set(self, i, item):
        walk = self._get_node(i)
        assert walk is not None
        walk.item = item

    def insert(self, i, item):
        if i < 0 or i > self._size:
            raise IndexError('Out of bounds')
        if i == 0:
            self.add_first(item)
        elif i == self._size:
            self.add_last(item)
        else:
            # before: head -> ... -> (i-1-th node: walk) -> (i-th node) -> ... -> tail
            # after:  head -> ... -> (i-1-th node: walk) -> Node(item) -> (i-th node) -> ... -> tail
            walk = self._get_node(i-1)
            ith = walk.next
            node = Node(item)
            walk.next = node
            node.next = ith
            self._size += 1

    def clear(self):
        while not self.is_empty():
            self.remove_first()

    def __iter__(self):
        return LinkedListIterator(self._head)


class LinkedListIterator:
    def __init__(self, head: Node):
        self.walk = head

    def __next__(self):
        if self.walk is None:
            raise StopIteration
        answer = self.walk.item
        self.walk = self.walk.next
        return answer
