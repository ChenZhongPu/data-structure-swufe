from unittest import TestCase
import unittest
from no_element import NoElement
from array_deque import ArrayDeque


class TestArrayDeque(TestCase):
    def test_operation(self):
        q = ArrayDeque()
        q.add_last(5)
        q.add_first(3)
        q.add_first(7)
        # [7, 3, 5]
        self.assertEqual(q.first(), 7)
        self.assertEqual(q.delete_last(), 5)
        # [7, 3]
        self.assertEqual(q.size(), 2)
        self.assertEqual(q.delete_first(), 7)
        # [3]
        self.assertEqual(q.delete_first(), 3)
        # []
        self.assertTrue(q.is_empty())


if __name__ == '__main__':
    unittest.main()
