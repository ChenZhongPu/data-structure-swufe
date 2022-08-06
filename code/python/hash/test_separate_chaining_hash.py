from unittest import TestCase
import unittest
from separate_chaining_hash import SeparateChainingHash


class TestSeparateChainingHash(TestCase):
    def test_operation(self):
        st = SeparateChainingHash()
        st[1] = '1'
        st[9] = '9'
        st[4] = '4'
        st[6] = '6'
        st[10] = '10'
        for i in range(20, 100):
            st[i] = str(i)
        self.assertFalse(st.is_empty())
        self.assertEqual(st[6], '6')
        st[6] = 'six'
        self.assertEqual(st[6], 'six')
        del st[6]
        self.assertFalse(st.contains(6))
        self.assertEqual(st.size(), 84)


if __name__ == '__main__':
    unittest.main()
