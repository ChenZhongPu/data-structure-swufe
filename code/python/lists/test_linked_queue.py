from unittest import TestCase
import unittest
from linked_queue import LinkedQueue
from no_element import NoElement


class TestLinkedQueue(TestCase):
    def test_operation(self):
        q = LinkedQueue()
        q.enqueue('D')
        q.enqueue('A')
        q.enqueue('T')
        q.enqueue('A')
        self.assertFalse(q.is_empty())
        self.assertEqual(q.peek(), 'D')
        self.assertEqual(q.dequeue(), 'D')
        self.assertEqual(q.size(), 3)
        self.assertEqual(q.dequeue(), 'A')
        self.assertEqual(q.dequeue(), 'T')
        self.assertEqual(q.dequeue(), 'A')
        self.assertRaises(NoElement, q.dequeue)
        self.assertTrue(q.is_empty())


if __name__ == '__main__':
    unittest.main()
