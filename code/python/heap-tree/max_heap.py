class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None
        self.parent = None

class MaxHeap:
    def __init__(self):
        self.root = None
        self.size = 0

    def insert(self, val):
        """Inserts a value into the heap."""
        new_node = TreeNode(val)
        self.size += 1
        
        if self.size == 1:
            self.root = new_node
            return

        # Find the parent of the new node
        # The new node will be at index 'self.size' (1-based)
        # The parent is at index 'self.size // 2'
        parent = self._get_node_at(self.size // 2)
        
        if not parent.left:
            parent.left = new_node
        else:
            parent.right = new_node
        new_node.parent = parent
        
        self._heapify_up(new_node)

    def extract_max(self):
        """Removes and returns the maximum value from the heap."""
        if self.size == 0:
            raise IndexError("extract_max from empty heap")
        
        max_val = self.root.val
        
        if self.size == 1:
            self.root = None
            self.size = 0
            return max_val

        # Replace root with the last node
        last_node = self._get_node_at(self.size)
        self.root.val = last_node.val
        
        # Remove the last node
        if last_node.parent.left == last_node:
            last_node.parent.left = None
        else:
            last_node.parent.right = None
        
        self.size -= 1
        self._heapify_down(self.root)
        
        return max_val

    def peek(self):
        """Returns the maximum value without removing it."""
        if self.size == 0:
            raise IndexError("peek from empty heap")
        return self.root.val

    def _heapify_up(self, node):
        """Restores the max-heap property by moving the node up."""
        while node.parent and node.val > node.parent.val:
            # Swap values
            node.val, node.parent.val = node.parent.val, node.val
            node = node.parent

    def _heapify_down(self, node):
        """Restores the max-heap property by moving the node down."""
        while True:
            largest = node
            if node.left and node.left.val > largest.val:
                largest = node.left
            if node.right and node.right.val > largest.val:
                largest = node.right
            
            if largest == node:
                break
            
            # Swap values
            node.val, largest.val = largest.val, node.val
            node = largest

    def _get_node_at(self, index):
        """Returns the node at the given 1-based index."""
        if index == 1:
            return self.root
        
        # We can find the path to the node using the binary representation of the index.
        # Ignore the most significant bit (which is always 1 for the root).
        # 0 -> left, 1 -> right
        path = bin(index)[3:] 
        current = self.root
        for char in path:
            if char == '0':
                current = current.left
            else:
                current = current.right
        return current
