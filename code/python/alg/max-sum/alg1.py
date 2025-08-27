def max_seq_max(lst):
    """An algorithm with cubic complexity O(n^3).
    >>> max_seq_max([-1, -2, -5, -3])
    0
    >>> max_seq_max([-1, 3, 4, -1, 2, 1, -5, 4])
    9
    >>> max_seq_max([1, 2, 3, 4, 5])
    15
    >>> max_seq_max([10, -2, 5, -8, 4])
    13
    >>> max_seq_max([-10, -5, 8, 2, -1, 4])
    13
    >>> max_seq_max([1, 2, -4, 3, 0, 2, -1])
    5
    >>> max_seq_max([2, -3, 4, -1, 5, -3, 2])
    8
    >>> max_seq_max([6, -5, 6, -8, 2])
    7
    """
    max_sum = 0
    for i in range(len(lst)):
        for j in range(i, len(lst)):
            this_sum = 0
            for k in range(i, j + 1):
                this_sum += lst[k]
            if this_sum > max_sum:
                max_sum = this_sum
    return max_sum


if __name__ == "__main__":
    import doctest

    doctest.testmod()
