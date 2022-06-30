import time
from fibonacci import fibonacci


if __name__ == '__main__':
    ns = [20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30]
    with open('fib_python.txt', 'w') as f:
        for n in ns:
            start = int(round(time.time() * 1000))
            fibonacci(n)
            end = int(round(time.time() * 1000))
            f.write(f'{n}   {end - start}\n')
