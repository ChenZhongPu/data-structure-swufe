from unittest import TestCase
import unittest
from rbt import RBT


class TestRBT(TestCase):
    def test_operation(self):
        rbt = RBT()
        for i in range(0, 1000, 2):
            rbt.put(i)
        self.assertLess(rbt.height(), 20)
        self.assertFalse(rbt.contains(555))
        self.assertTrue(rbt.contains(666))

        rbt.remove(444)
        self.assertFalse(rbt.contains(444))
        rbt.remove(1)
        rbt.remove(10)
        rbt.remove(12)
        rbt.remove(100)
        self.assertEqual(rbt.size(), 496)


if __name__ == '__main__':
    unittest.main()
