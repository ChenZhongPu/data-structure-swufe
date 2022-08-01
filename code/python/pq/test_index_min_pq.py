from unittest import TestCase
import unittest
from index_min_pq import IndexMinPQ


class TestIndexMinPQ(TestCase):
    def test_operation(self):
        min_pq = IndexMinPQ(10)
        min_pq.insert(1, 'Math')
        min_pq.insert(2, 'Data')
        min_pq.insert(3, 'English')
        min_pq.insert(5, 'Biology')
        min_pq.insert(6, 'History')

        self.assertTrue(min_pq.contains(1))
        self.assertFalse(min_pq.contains(4))
        self.assertEqual(min_pq.min_index(), 5)
        self.assertEqual(min_pq.min_key(), 'Biology')
        self.assertEqual(min_pq.key_of(3), 'English')

        min_pq.del_min()
        self.assertEqual(min_pq.min_index(), 2)
        self.assertEqual(min_pq.min_key(), 'Data')
        self.assertEqual(min_pq.size(), 4)

        min_pq.change_key(1, 'Child Math')
        self.assertEqual(min_pq.min_index(), 1)
        self.assertEqual(min_pq.min_key(), 'Child Math')


if __name__ == '__main__':
    unittest.main()
