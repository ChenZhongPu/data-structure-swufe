from unittest import TestCase
import unittest
from circular_linked_list import CircularLinkedList
from no_element import NoElement


class TestCircularLinkedList(TestCase):
    def test_operation(self):
        circular_list = CircularLinkedList()
        circular_list.add_last('structures')
        circular_list.add_last('is')
        circular_list.add_last('fun')
        circular_list.add_last('!')
        self.assertEqual(circular_list.size(), 4)
        self.assertEqual(circular_list.first(), 'structures')
        self.assertEqual(circular_list.last(), '!')
        circular_list.add_first('data')

        circular_list.remove_first()
        circular_list.remove_first()
        circular_list.remove_first()
        circular_list.remove_first()
        self.assertEqual(circular_list.size(), 1)
        circular_list.remove_first()
        self.assertTrue(circular_list.is_empty())

        circular_list.add_first('!')
        circular_list.add_first('fun')
        circular_list.add_first('is')
        circular_list.add_first('structures')
        circular_list.add_first('data')
        self.assertEqual(circular_list.first(), 'data')
        self.assertEqual(circular_list.last(), '!')
        self.assertEqual(circular_list.size(), 5)

        for s in circular_list:
            print(s)

        circular_list.remove_last()
        self.assertEqual(circular_list.last(), 'fun')
        circular_list.remove_last()
        self.assertEqual(circular_list.last(), 'is')
        circular_list.remove_last()
        self.assertEqual(circular_list.last(), 'structures')
        circular_list.remove_last()
        self.assertEqual(circular_list.last(), 'data')
        circular_list.remove_last()
        self.assertRaises(NoElement, circular_list.remove_last)

        circular_list.add_last('structures')
        circular_list.add_last('is')
        circular_list.add_last('fun')
        circular_list.add_last('!')
        circular_list.rotate()
        self.assertEqual(circular_list.last(), 'structures')


if __name__ == '__main__':
    unittest.main()
