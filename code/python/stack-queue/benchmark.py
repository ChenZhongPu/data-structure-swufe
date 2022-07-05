import random
import datetime
from queue import Queue


def queue_vary_size():
    def time_dequeue(n):
        q = Queue()
        for _ in range(n):
            q.enqueue(random.randint(1, n))
        start = datetime.datetime.now()
        # to avoid the randomness of single operation
        # we repeat it 20 times
        for _ in range(20):
            q.dequeue()
        end = datetime.datetime.now()
        return int(round((end - start).total_seconds() * 1000))

    with open('benchmark_queue.txt', 'w') as f:
        for size in [1000000, 2000000, 3000000, 4000000, 5000000]:
            t = time_dequeue(size)
            f.write(f'{size}   {t}\n')


queue_vary_size()
