from unittest import TestCase
import unittest
from linked_list import LinkedList


class TestLinkedList(TestCase):
    def test_operation(self):
        ll = LinkedList()
        ll.add_first('fun')
        ll.add_first('is')
        ll.add_first('structures')
        ll.add_first('data')
        self.assertEqual(ll.size(), 4)
        self.assertEqual(ll.index_of('fun'), 3)
        self.assertEqual(ll.get(0), 'data')
        ll.remove_first()
        self.assertEqual(ll.get(0), 'structures')
        ll.add_last('!')
        self.assertRaises(IndexError, lambda: ll.set(4, 'great'))
        ll.set(3, '.')
        for v in ll:
            print(v)
        print('---')
        ll.remove_last()
        ll.remove_last()
        ll.remove_first()
        ll.remove_last()
        self.assertTrue(ll.is_empty())
        self.assertEqual(ll.size(), 0)

        ll.add_last('linked')
        ll.add_last('list')
        ll.add_first('is')
        self.assertEqual(ll.size(), 3)
        ll.insert(1, 'a')
        for v in ll:
            print(v)
        print('---')
        ll.insert(0, 'this')
        self.assertEqual(ll.get(0), 'this')
        ll.insert(5, '!')
        self.assertEqual(ll.size(), 6)
        self.assertEqual(ll.get(5), '!')
        for v in ll:
            print(v)

        ll.clear()
        self.assertTrue(ll.is_empty())


if __name__ == '__main__':
    unittest.main()
