class BST2:
    """A binary search tree"""

    class Node:
        """A node in a binary search tree"""

        def __init__(self, key):
            self.key = key
            self.left = None
            self.right = None

    def __init__(self):
        self.root = None

    @staticmethod
    def _search_in_subtree(node, k):
        assert node is not None
        if k == node.key:
            return node
        elif k < node.key and node.left is not None:
            return BST2._search_in_subtree(node.left, k)
        elif k > node.key and node.right is not None:
            return BST2._search_in_subtree(node.right, k)
        return node

    def insert(self, k):
        if self.root is None:
            self.root = BST2.Node(k)
        else:
            p = BST2._search_in_subtree(self.root, k)
            if k < p.key:
                p.left = BST2.Node(k)
            elif k > p.key:
                p.right = BST2.Node(k)

    def search(self, k):
        """Return the node for key k if is in the tree, or None otherwise."""

        def _search(node):
            if node is None or k == node.key:
                return node
            elif k < node.key:
                return _search(node.left)
            else:
                return _search(node.right)

        return _search(self.root)

    def in_order_print(self):
        def _in_order_rec(node):
            if node is not None:
                _in_order_rec(node.left)
                print(node.key)
                _in_order_rec(node.right)

        _in_order_rec(self.root)


if __name__ == "__main__":
    bst = BST2()
    bst.insert(4)
    bst.insert(2)
    bst.insert(6)
    bst.insert(10)
    bst.in_order_print()
