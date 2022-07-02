from unittest import TestCase
import unittest
import binary_search


class Test(TestCase):
    def test_search(self):
        a = [1, 2, 4, 5, 6, 9, 10]
        self.assertEqual(binary_search.search(a, 1), 0)
        self.assertEqual(binary_search.search(a, 10), 6)
        self.assertEqual(binary_search.search(a, 5), 3)
        self.assertEqual(binary_search.search(a, 3), -1)

    def one_hit(self):
        a = [5]
        self.assertEqual(binary_search.search(a, 5), 0)

    def one_miss(self):
        a = [5]
        self.assertEqual(binary_search.search(a, 4), -1)

    def empty(self):
        a = []
        self.assertEqual(binary_search.search(a, 1), -1)


if __name__ == '__main__':
    unittest.main()
