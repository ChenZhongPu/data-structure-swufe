def search(a, target):
    """
    Binary search.

    :param a: a sorted list
    :param target
    :return: the index of `target` (-1 if not found)
    """
    high = len(a) - 1
    low = 0
    while high >= low:
        mid = low + (high - low) // 2
        if a[mid] == target:
            return mid
        elif a[mid] > target:
            high = mid - 1
        else:
            low = mid + 1
    return -1
