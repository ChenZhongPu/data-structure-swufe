from unittest import TestCase
import unittest
from max_pq import MaxPQ


class TestMaxPQ(TestCase):
    def test_operation(self):
        pq = MaxPQ()
        self.assertTrue(pq.is_empty())
        pq.insert(1)
        pq.insert(9)
        pq.insert(4)
        pq.insert(9)
        pq.insert(6)
        pq.insert(11)
        pq.insert(3)
        pq.insert(7)

        self.assertEqual(pq.size(), 8)
        self.assertEqual(pq.max(), 11)
        self.assertEqual(pq.del_max(), 11)
        self.assertEqual(pq.max(), 9)


if __name__ == '__main__':
    unittest.main()
