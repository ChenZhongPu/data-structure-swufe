from unittest import TestCase
import unittest
from linked_stack import LinkedStack
from no_element import NoElement


class TestLinkedStack(TestCase):
    def test_operation(self):
        s = LinkedStack()
        self.assertEqual(s.size(), 0)
        s.push(5)
        self.assertFalse(s.is_empty())
        self.assertEqual(s.size(), 1)
        s.push(3)
        self.assertEqual(s.peek(), 3)
        self.assertEqual(s.pop(), 3)
        self.assertEqual(s.pop(), 5)
        self.assertRaises(NoElement, s.pop)
        self.assertEqual(s.size(), 0)
        self.assertTrue(s.is_empty())


if __name__ == '__main__':
    unittest.main()
