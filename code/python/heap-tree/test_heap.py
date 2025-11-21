import unittest
from max_heap import MaxHeap

class TestMaxHeap(unittest.TestCase):
    def test_heap_operations(self):
        heap = MaxHeap()
        
        # Test insert
        values = [10, 5, 20, 3, 8, 30]
        for v in values:
            heap.insert(v)
            
        # Tree structure should be:
        #       30
        #     /    \
        #   10      20
        #  /  \    /
        # 3    5  8
        # (Note: exact structure depends on insertion order and heapify, 
        # but 30 must be root)
        
        self.assertEqual(heap.peek(), 30)
        
        # Test extract_max (should return in descending order)
        expected = [30, 20, 10, 8, 5, 3]
        extracted = []
        while heap.size > 0:
            extracted.append(heap.extract_max())
            
        self.assertEqual(extracted, expected)

    def test_empty_heap(self):
        heap = MaxHeap()
        with self.assertRaises(IndexError):
            heap.peek()
        with self.assertRaises(IndexError):
            heap.extract_max()

    def test_single_element(self):
        heap = MaxHeap()
        heap.insert(42)
        self.assertEqual(heap.peek(), 42)
        self.assertEqual(heap.extract_max(), 42)
        self.assertEqual(heap.size, 0)

if __name__ == '__main__':
    unittest.main()
