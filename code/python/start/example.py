def example1(s):
    """Return the sum of the elements in sequence s"""
    n = len(s)
    total = 0
    for j in range(n):
        total += s[j]
    return total


def example2(s):
    """Return the sum of the elements with even index in sequence s"""
    n = len(s)
    total = 0
    for j in range(0, n, 2):
        total += s[j]
    return total


def example3(s):
    """Return the sum of the prefix sums of sequences s"""
    n = len(s)
    total = 0
    for j in range(n):
        for k in range(1 + j):
            total += s[k]
    return total


def example4(s):
    """Return the sum of the prefix sums of sequences s"""
    n = len(s)
    prefix = 0
    total = 0
    for j in range(n):
        prefix += s[j]
        total += prefix
    return total


# assume that a and b have equal length
def example5(a, b):
    """Return the number of elements in b equal to the sum of prefix sums in a"""
    n = len(a)
    count = 0
    for i in range(n):
        total = 0
        for j in range(n):
            for k in range(1+j):
                total += a[k]
        if b[i] == total:
            count += 1
    return count
