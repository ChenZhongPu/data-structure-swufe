from no_element import NoElement


class AVL:
    """
    AVL trees where the heights of the two child subtrees of any node
    differ by at most one.
    """

    class Node:
        def __init__(self, key, height=0, left=None, right=None):
            self.key = key
            self.height = height
            self.left = left
            self.right = right

    def __init__(self):
        self._root = None

    def is_empty(self):
        return self._root is None

    @staticmethod
    def _get_height(node: Node):
        if node is None:
            return -1
        else:
            return node.height

    def height(self):
        return AVL._get_height(self._root)

    @staticmethod
    def _get_balance_factor(node: Node):
        if node is None:
            return 0
        else:
            return AVL._get_height(node.left) - AVL._get_height(node.right)

    def get(self, key):
        def _get(x: AVL.Node):
            if x is None or key == x.key:
                return x
            if key < x.key:
                return _get(x.left)
            else:
                return _get(x.right)
        if key is None:
            raise KeyError
        return _get(self._root)

    def contains(self, key):
        return self.get(key) is not None

    @staticmethod
    def _right_rotate(y: Node):
        x = y.left
        y.left = x.right
        x.right = y
        y.height = max(AVL._get_height(y.left), AVL._get_height(y.right)) + 1
        x.height = max(AVL._get_height(x.left), AVL._get_height(x.right)) + 1
        return x

    @staticmethod
    def _left_rotate(x: Node):
        y = x.right
        x.right = y.left
        y.left = x
        x.height = max(AVL._get_height(x.left), AVL._get_height(x.right)) + 1
        y.height = max(AVL._get_height(y.left), AVL._get_height(y.right)) + 1
        return y

    def put(self, key):
        def _put(x: AVL.Node):
            if x is None:
                return AVL.Node(key)
            elif key < x.key:
                x.left = _put(x.left)
            elif key > x.key:
                x.right = _put(x.right)
            # update the height
            x.height = max(AVL._get_height(x.left), AVL._get_height(x.right)) + 1
            # get the balance factor
            bf = AVL._get_balance_factor(x)
            if bf > 1 and key < x.left.key:  # case 1
                return AVL._right_rotate(x)
            if bf < -1 and key > x.right.key:  # case 2
                return AVL._left_rotate(x)
            if bf > 1 and key > x.left.key:  # case 3
                x.left = AVL._left_rotate(x.left)
                return AVL._right_rotate(x)
            if bf < -1 and key < x.right.key:  # case 4
                x.right = AVL._right_rotate(x.right)
                return AVL._left_rotate(x)
            return x

        self._root = _put(self._root)
        assert self.is_avl()

    @staticmethod
    def _min(x: Node):
        # assume x is not None
        if x.left is None:
            return x
        return AVL._min(x.left)

    def min(self):
        if self.is_empty():
            raise NoElement
        return AVL._min(self._root).key

    @staticmethod
    def _remove_min(x: Node):
        # assume x is not None
        if x.left is None:
            return x.right
        x.left = AVL._remove_min(x.left)
        x.height = max(AVL._get_height(x.left), AVL._get_height(x.right)) + 1
        return x

    def remove(self, key):
        def _remove(x: AVL.Node):
            if x is None:
                return None
            if key < x.key:
                x.left = _remove(x.left)
            elif key > x.key:
                x.right = _remove(x.right)
            else:
                if x.right is None:
                    return x.left
                if x.left is None:
                    return x.right
                t = x
                x = AVL._min(t.right)
                x.right = AVL._remove_min(t.right)
                x.left = t.left
            x.height = max(AVL._get_height(x.left), AVL._get_height(x.right)) + 1
            bf = AVL._get_balance_factor(x)
            if bf > 1 and AVL._get_balance_factor(x.left) >= 0:  # case 1: left-left
                return AVL._right_rotate(x)
            if bf < -1 and AVL._get_balance_factor(x.right) <= 0:  # case 2: right-right
                return AVL._left_rotate(x)
            if bf > 1 and AVL._get_balance_factor(x.left) < 0:  # case 3: left-right
                x.left = AVL._left_rotate(x.left)
                return AVL._right_rotate(x)
            if bf < -1 and AVL._get_balance_factor(x.right) > 0:  # case 4: right-left
                x.right = AVL._right_rotate(x.right)
                return AVL._left_rotate(x)
            return x
        self._root = _remove(self._root)
        assert self.is_avl()

    @staticmethod
    def _is_avl(x: Node):
        if x is None:
            return True
        return abs(AVL._get_balance_factor(x)) < 2 and AVL._is_avl(x.left) and AVL._is_avl(x.right)

    def is_avl(self):
        return AVL._is_avl(self._root)
