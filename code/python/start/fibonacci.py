import time
import timeit


def fibonacci(n):
    assert n >= 0
    if n == 0:
        return 0
    elif n == 1 or n == 2:
        return 1
    else:
        return fibonacci(n - 1) + fibonacci(n - 2)


if __name__ == "__main__":
    start = round(time.time() * 1000)
    fibonacci(30)
    end = round(time.time() * 1000)
    elapse = end - start
    print(f"it runs in {elapse:.3f} ms")
    # run it 1000 times, the value is seconds as a float
    elapse = timeit.timeit(
        "fibonacci(30)", setup="from __main__ import fibonacci", number=1000
    )
    print(f"it runs in {elapse:.3f} ms")
