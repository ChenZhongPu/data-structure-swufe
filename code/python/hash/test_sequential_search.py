from unittest import TestCase
import unittest
from sequential_search import SequentialSearch


class TestSequentialSearch(TestCase):
    def test_operation(self):
        search = SequentialSearch()
        self.assertTrue(search.is_empty())
        search[1] = '1'
        search[8] = '8'
        search[9] = '9'
        search[6] = '6'
        self.assertEqual(search[8], '8')
        search[8] = 'eight'
        self.assertEqual(search[8], 'eight')
        del search[8]
        self.assertFalse(search.contains(8))
        self.assertEqual(search.size(), 3)

        for k in search.keys():
            print(k)


if __name__ == '__main__':
    unittest.main()
