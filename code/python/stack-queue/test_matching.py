from unittest import TestCase
import unittest
import matching


class Test(TestCase):
    def test_is_matched(self):
        self.assertTrue(matching.is_matched('()(()){([()])}'))
        self.assertFalse(matching.is_matched('({[])}'))
        self.assertFalse(matching.is_matched('('))


if __name__ == '__main__':
    unittest.main()
