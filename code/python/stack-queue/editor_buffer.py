"""
Text editor buffer

It is adapted from 1.3.44, Algorithms, 4th Edition, by Robert Sedgewick and Kevin Wayne.
"""


class EditorBuffer:
    def __init__(self):
        pass

    def insert(self, c: str):
        """insert c at the cursor position"""
        assert len(c) == 1
        pass

    def delete(self):
        """delete and return the character at the cursor"""
        pass

    def move_left(self, k: int):
        """move the cursor k positions to the left"""
        pass

    def move_right(self, k: int):
        """move the cursor k positions to the right"""
        pass

    def __len__(self):
        """number of characters in the buffer"""
        pass


if __name__ == "__main__":
    """a demo usage of EditorBuffer"""
    buffer = EditorBuffer()
    buffer.insert("a")
    buffer.insert("b")
    buffer.insert("c")
    assert len(buffer) == 3
    buffer.move_left(1)
    assert buffer.delete() == "b"
    assert len(buffer) == 2
    buffer.move_right(1)
    buffer.insert("d")
    assert buffer.delete() == "d"
