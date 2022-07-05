from unittest import TestCase
import unittest
from no_element import NoElement
from queue import Queue


class TestQueue(TestCase):
    def test_operation(self):
        q = Queue()
        q.enqueue('D')
        q.enqueue('A')
        q.enqueue('T')
        q.enqueue('A')
        self.assertFalse(q.is_empty())
        self.assertEqual(q.dequeue(), 'D')
        self.assertEqual(q.size(), 3)
        self.assertEqual(q.dequeue(), 'A')
        self.assertEqual(q.dequeue(), 'T')
        self.assertEqual(q.dequeue(), 'A')
        self.assertRaises(NoElement, q.dequeue)
        self.assertTrue(q.is_empty())


if __name__ == '__main__':
    unittest.main()
