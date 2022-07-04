from unittest import TestCase
import unittest
from stack import Stack


class TestStack(TestCase):
    def test_operation(self):
        s = Stack()
        self.assertEqual(s.size(), 0)
        s.push(5)
        self.assertFalse(s.is_empty())
        self.assertEqual(s.size(), 1)
        s.push(3)
        self.assertEqual(s.pop(), 3)
        self.assertEqual(s.pop(), 5)
        self.assertEqual(s.pop(), None)
        self.assertEqual(s.size(), 0)
        self.assertTrue(s.is_empty())


if __name__ == '__main__':
    unittest.main()
