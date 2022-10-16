from unittest import TestCase
import unittest
from avl import AVL


class TestAVL(TestCase):
    def test_operation(self):
        avl = AVL()
        for i in range(0, 1000, 2):
            avl.put(i)
        self.assertLess(avl.height(), 20)
        self.assertTrue(avl.contains(666))
        self.assertFalse(avl.contains(555))
        avl.remove(444)
        self.assertFalse(avl.contains(444))
        for i in range(0, 1000, 50):
            avl.remove(i)
        self.assertLess(avl.height(), 20)


if __name__ == '__main__':
    unittest.main()
