from no_element import NoElement


class Node:
    def __init__(self, item, nxt=None):
        self.item = item
        self.next = nxt


class LinkedStack:
    """A stack based on singly linked list."""

    def __init__(self):
        self._head = None
        self._size = 0

    def size(self):
        return self._size

    def is_empty(self):
        return self._size == 0

    # push() = add_first()
    def push(self, item):
        node = Node(item)
        node.next = self._head
        self._head = node
        self._size += 1

    # pop() = remove_first()
    def pop(self):
        if self.is_empty():
            raise NoElement('Pop from an empty stack')
        answer = self._head.item
        self._head = self._head.next
        self._size -= 1
        return answer

    def peek(self):
        if self.is_empty():
            raise NoElement('Peek from an empty stack')
        return self._head.item

    def __iter__(self):
        return LinkedIterator(self._head)


class LinkedIterator:
    def __init__(self, head: Node):
        self.walk = head

    def __next__(self):
        if self.walk is None:
            raise StopIteration
        answer = self.walk.item
        self.walk = self.walk.next
        return answer
