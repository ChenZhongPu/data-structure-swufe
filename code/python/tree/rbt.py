class RBT:
    """A red-black tree."""

    RED = False
    BLACK = True

    class Node:
        """Node entry for RBT."""

        def __init__(self, key=None):
            self.key = key
            self.color = RBT.BLACK
            self.left = None
            self.right = None
            self.p = None

    def __init__(self):
        self._NIL = RBT.Node()
        self._root = self._NIL
        self._size = 0

    def size(self):
        return self._size

    def is_empty(self):
        return self._size == 0

    def get(self, key):
        x = self._root
        while x is not self._NIL and key != x.key:
            if key < x.key:
                x = x.left
            else:
                x = x.right
        return x

    def contains(self, key):
        if key is None:
            raise KeyError
        return self.get(key) is not self._NIL

    def _left_rotate(self, x: Node):
        y = x.right
        x.right = y.left
        if y.left is not self._NIL:
            y.left.p = x
        y.p = x.p
        if x.p is self._NIL:
            self._root = y
        elif x is x.p.left:
            x.p.left = y
        else:
            x.p.right = y
        y.left = x
        x.p = y

    def _right_rotate(self, y: Node):
        x = y.left
        y.left = x.right
        if x.right is not self._NIL:
            x.right.p = y
        x.p = y.p
        if y.p is self._NIL:
            self._root = x
        elif y is y.p.left:
            y.p.left = x
        else:
            y.p.right = x
        x.right = y
        y.p = x

    def put(self, key):
        x = self._root
        y = self._NIL
        while x is not self._NIL:
            y = x
            if key < x.key:
                x = x.left
            elif key > x.key:
                x = x.right
            else:
                return
        z = RBT.Node(key)
        z.p = y
        if y is self._NIL:
            self._root = z
        elif z.key < y.key:
            y.left = z
        else:
            y.right = z
        z.left = self._NIL
        z.right = self._NIL
        z.color = RBT.RED
        self._size += 1
        self._insert_fixup(z)

    def _insert_fixup(self, z: Node):
        while z.p.color == RBT.RED:
            if z.p is z.p.p.left:
                y = z.p.p.right
                if y.color == RBT.RED:
                    z.p.color = RBT.BLACK
                    y.color = RBT.BLACK
                    z.p.p.color = RBT.RED
                    z = z.p.p
                else:
                    if z is z.p.right:
                        z = z.p
                        self._left_rotate(z)
                    z.p.color = RBT.BLACK
                    z.p.p.color = RBT.RED
                    self._right_rotate(z.p.p)
            else:
                y = z.p.p.left
                if y.color == RBT.RED:
                    z.p.color = RBT.BLACK
                    y.color = RBT.BLACK
                    z.p.p.color = RBT.RED
                    z = z.p.p
                else:
                    if z is z.p.left:
                        z = z.p
                        self._right_rotate(z)
                    z.p.color = RBT.BLACK
                    z.p.p.color = RBT.RED
                    self._left_rotate(z.p.p)
        self._root.color = RBT.BLACK

    def _min(self, x: Node):
        while x.left is not self._NIL:
            x = x.left
        return x

    def _transplant(self, u: Node, v: Node):
        if u.p is self._NIL:
            self._root = v
        elif u is u.p.left:
            u.p.left = v
        else:
            u.p.right = v
        v.p = u.p

    def _delete(self, z: Node):
        if z.left is not self._NIL and z.right is not self._NIL:
            y = self._min(z.right)
            z.key = y.key
            z = y
        x = z.left if z.left is not self._NIL else z.right
        self._transplant(z, x)
        self._delete_fixup(x)

    def _delete_fixup(self, x: Node):
        while x is not self._root and x.color == RBT.BLACK:
            if x is x.p.left:
                w = x.p.right
                if w.color == RBT.RED:
                    w.color = RBT.BLACK
                    x.p.color = RBT.RED
                    self._left_rotate(x.p)
                    w = x.p.right
                if w.left.color == RBT.BLACK and w.right.color == RBT.BLACK:
                    w.color = RBT.RED
                    x = x.p
                else:
                    if x.right.color == RBT.BLACK:
                        x.left.color = RBT.BLACK
                        w.color = RBT.RED
                        self._right_rotate(w)
                        w = x.p.right
                    w.color = x.p.color
                    x.p.color = RBT.BLACK
                    w.right.color = RBT.BLACK
                    self._left_rotate(x.p)
                    x = self._root
            else:
                w = x.p.left
                if w.color == RBT.RED:
                    w.color = RBT.BLACK
                    x.p.color = RBT.RED
                    self._right_rotate(x.p)
                    w = x.p.left
                if w.right.color == RBT.BLACK and w.left.color == RBT.BLACK:
                    w.color = RBT.RED
                    x = x.p
                else:
                    if w.left.color == RBT.BLACK:
                        w.right.color = RBT.BLACK
                        w.color = RBT.RED
                        self._left_rotate(w)
                        w = x.p.left
                    w.color = x.p.color
                    x.p.color = RBT.BLACK
                    x.left.color = RBT.BLACK
                    self._right_rotate(x.p)
                    x = self._root
        x.color = RBT.BLACK

    def remove(self, key):
        if key is None:
            raise KeyError
        z = self.get(key)
        if z is not self._NIL:
            self._delete(z)
            self._size -= 1

    def height(self):
        def _height(x: RBT.Node):
            if x is self._NIL:
                return -1
            else:
                return 1 + max(_height(x.left), _height(x.right))
        return _height(self._root)

    def in_order(self):
        def _in_order(x: RBT.Node):
            if x is not self._NIL:
                _in_order(x.left)
                print(x.key)
                _in_order(x.right)
        _in_order(self._root)

