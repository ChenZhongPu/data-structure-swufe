"""
A simple class that wraps a list and provides some convenience methods.

>>> l = MyList()
>>> l.add(1)
>>> l.add(2)
>>> len(l)
2
>>> size(l)
2
>>> l[0]
1
"""


class MyList:
    def __init__(self):
        self.data = []

    def add(self, value):
        self.data.append(value)

    def __len__(self):
        return len(self.data)

    def size(self):
        return len(self.data)

    def __getitem__(self, index):
        return self.data[index]
