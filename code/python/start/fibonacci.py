import time


def fibonacci(n):
    assert n >= 0
    if n == 0:
        return 0
    elif n == 1 or n == 2:
        return 1
    else:
        return fibonacci(n - 1) + fibonacci(n - 2)


if __name__ == '__main__':
    start = int(round(time.time() * 1000))
    fibonacci(30)
    end = int(round(time.time() * 1000))
    elapse = end - start
    print(f'it runs in {elapse} ms')
