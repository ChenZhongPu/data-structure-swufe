from separate_chaining_hash import SeparateChainingHash
from sequential_search import SequentialSearch
import random
import time


def evaluate(size):
    seq_st = SequentialSearch()
    hast_st = SeparateChainingHash()
    for _ in range(size):
        k = random.randint(0, 2 * size)
        seq_st[k] = str(k)
        hast_st[k] = str(k)

    query = random.sample(range(2 * size), 100)
    start = int(round(time.time() * 1000))
    for k in query:
        _ = seq_st[k]
    end = int(round(time.time() * 1000))
    seq_time = end - start

    start = int(round(time.time() * 1000))
    for k in query:
        _ = hast_st[k]
    end = int(round(time.time() * 1000))

    return seq_time, end - start


if __name__ == '__main__':
    s = [10000, 20000, 30000, 40000, 50000]
    with open('st_time.txt', 'w') as f:
        for i in s:
            t = evaluate(i)
            f.write(f'{i}   {t[0]}  {t[1]}\n')
