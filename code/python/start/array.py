class ArrError(Exception):
    pass


class Array:
    def __init__(self, n):
        self._data = [0] * n
        self._size = n

    def __len__(self):
        return self._size

    def __getitem__(self, i):
        return self._data[i]

    def __setitem__(self, value, i):
        self._data[i] = value


if __name__ == "__main__":
    arr = Array(5)
    print(len(arr))
    arr[4] = 7
    print(arr[4])
    for i in arr:
        print(i)
