from unittest import TestCase
import unittest
from bst import BST


class TestBST(TestCase):
    def test_operation(self):
        bst = BST()
        self.assertTrue(bst.is_empty())
        bst.put(12)
        bst.put(5)
        bst.put(2)
        self.assertEqual(bst.size(), 3)
        bst.put(9)
        bst.put(18)
        bst.put(19)
        bst.put(9)
        bst.put(15)
        bst.put(17)
        bst.put(13)
    #            12
    #          /    \
    #        5      18
    #      /  \    /  \
    #     2    9  15  19
    #             / \
    #           13   17
        self.assertEqual(bst.size(), 9)
        self.assertEqual(bst.height(), 3)
        self.assertTrue(bst.contains(9))
        self.assertFalse(bst.contains(7))

        self.assertEqual(bst.min(), 2)
        self.assertEqual(bst.max(), 19)
        bst.remove_min()
        self.assertEqual(bst.min(), 5)
        bst.remove_max()
        self.assertEqual(bst.max(), 18)

        bst.remove(9)
        self.assertFalse(bst.contains(9))
        self.assertEqual(bst.size(), 6)


if __name__ == '__main__':
    unittest.main()
