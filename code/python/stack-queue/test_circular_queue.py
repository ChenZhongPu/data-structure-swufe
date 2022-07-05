from unittest import TestCase
import unittest
from no_element import NoElement
from circular_queue import CircularQueue


class TestCircularQueue(TestCase):
    def test_operation(self):
        q = CircularQueue()
        q.enqueue('data')
        q.enqueue('structures')
        q.enqueue('is')
        q.enqueue('fun')
        self.assertFalse(q.is_empty())
        self.assertEqual(q.dequeue(), 'data')
        self.assertEqual(q.size(), 3)
        self.assertEqual(q.dequeue(), 'structures')
        self.assertEqual(q.dequeue(), 'is')
        self.assertEqual(q.dequeue(), 'fun')
        self.assertRaises(NoElement, q.dequeue)
        self.assertTrue(q.is_empty())
        for i in range(20):
            q.enqueue(i)
        self.assertEqual(q.size(), 20)
        for i in q:
            print(i)


if __name__ == '__main__':
    unittest.main()
