from unittest import TestCase
import evaluate
import unittest


class Test(TestCase):
    def test_compute(self):
        self.assertAlmostEqual(evaluate.compute('( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )'), 101)
        self.assertAlmostEqual(evaluate.compute('( ( 1 / 2 ) + ( 4 - 2 ) )'), 2.5)


if __name__ == '__main__':
    unittest.main()
