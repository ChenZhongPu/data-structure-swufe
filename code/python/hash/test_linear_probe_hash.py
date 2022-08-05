from unittest import TestCase
import unittest
from linear_probe_hash import LinearProbeHash


class TestLinearProbeHash(TestCase):
    def test_operation(self):
        table = LinearProbeHash()
        table[1] = '1'
        table[2] = '2'
        table[3] = '3'
        table[16] = '16'
        table[6] = '6'
        self.assertEqual(table[16], '16')
        self.assertTrue(table.size(), 5)
        table[6] = 'six'
        self.assertEqual(table[6], 'six')
        del table[16]
        self.assertEqual(table.size(), 4)
        self.assertEqual(table[16], None)


if __name__ == '__main__':
    unittest.main()


