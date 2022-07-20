from unittest import TestCase
import unittest
from no_element import NoElement
from doubly_linked_list import DoublyLinkedList


class TestDoublyLinkedList(TestCase):
    def test_operation(self):
        d_list = DoublyLinkedList()
        d_list.add_first('fun')
        d_list.add_first('is')
        d_list.add_first('structures')
        d_list.add_first('data')
        self.assertEqual(d_list.size(), 4)
        self.assertEqual(d_list.first(), 'data')
        self.assertEqual(d_list.last(), 'fun')

        d_list.remove_first()
        d_list.remove_last()
        self.assertEqual(d_list.size(), 2)
        self.assertEqual(d_list.first(), 'structures')
        self.assertEqual(d_list.last(), 'is')

        d_list.add_last('really')
        d_list.add_last('fun')
        self.assertEqual(d_list.last(), 'fun')
        for s in d_list:
            print(s)

        d_list.remove_last()
        d_list.remove_last()
        d_list.remove_first()
        d_list.remove_first()
        self.assertTrue(d_list.is_empty())
        self.assertRaises(NoElement, d_list.remove_first)


if __name__ == '__main__':
    unittest.main()
