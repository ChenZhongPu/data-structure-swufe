from unittest import TestCase
import unittest
from add import add


class Test(TestCase):
    def test_add(self):
        self.assertEqual(add(2, 1), 3)

    def test_add_negatives(self):
        self.assertEqual(add(-2, -2), -4)


if __name__ == '__main__':
    unittest.main()
