# Hash Functions
We seek a hash function that both is easy to compute and uniformly distributes the keys. 

## Independent uniform hashing
> The hash function that we use uniformly and independently distribute keys among the integer values between `0` and `m - 1`.

We call such an idea hash function an **independent uniform hash function**. Independent uniform hashing is an ideal abstraction, but it is not something that can reasonably be implemented in practice. So, we would like a *good* hash function to approximately satisfy the assumption of independent uniform hashing. Unfortunately, you typically have no way to check this condition, unless you happen to know the probability distribution from which the keys are drawn.

In this book, we only introduce **static hashing**, which uses a single, fixed hash function. For more information, such as *random hashing*, readers can refer to *Introduction to Algorithms*.

Clearly, we need a different hash function for each key type that we use. For example, if the key is a number, we can use the *modular* method in the previous section; if the key is a string, we need to convert the string into a number; if the key has multiple parts, we need to combine the parts somehow.

Let's recap with the goal of a *hash function* again: map a key *k* to an integer in the range \\([0, m-1]\\). Therefore, it is common to view the evaluation of a hash function, *h(k)*, as consisting of two portions:

- a **hash code** that maps a key *k* to an integer
- a **compression function** that maps the hash code to an integer within a range of indices \\([0, m-1]\\) for a bucket array

## Hash code
### Integer
Both Java and Python rely on 32-bit hash codes, so for base types `byte`, `short`, `int`, and `char`, we can achieve a good hash code simply by casting a value to `int`. So the hash code of `123` is simply `123`.

As for `float`, we can **treat its bit representation as an integer**. In Java, this is done by `Float.floatToIntBits(x)` given a float `x`. In Python, this can be done by the following code:

```python
import struct

def float_to_int_bits(f):
    s = struct.pack('>f', f)
    return struct.unpack('>l', s)[0]
```

If the key is `double`, who is 64-bit, a common method is to take the exclusive-or of the high-order (32-bit) and low-order (32-bit). This is how Java does:

```java
public static int hashCode(double value) {
    long bits = doubleToLongBits(value);
    return (int)(bits ^ (bits >>> 32));
}
```

### String
As for character strings or other variable-length objects that can be viewed as tuples of the form \\((x_0, x_1, x_2, \dots, x_{n-1})\\), where the order of the \\(x_i\\) is significant. To compute the hash code, we choose a non-zero constant \\(a \neq 1\\):

\\[x_{0}a^{n-1} + x_{1}a^{n-2} + \dots + x_{n-2}a + x_{n-1}\\]

This method is also known as **polynomial hash code**. The following is how Java does when the string is in Latin encode[^mask]:

```java
public static int hashCode(byte[] value) {
    int h = 0;
    for (byte v : value) {
        h = 31 * h + v;
    }
    return h;
}
```

### Compound keys
If the key type has multiple fields, we can typically mix them together in the way just described for `String` values.

```java
class PhoneNumber {
    private short areaCode;
    private short prefix;
    private short lineNum;
    ...
}
```

Then its hash code can be computed as:

\\[(31 \times areaCode + prefix) \times 31 + lineNum\\]

## Compression function
Since our goal is an array index, not a 32-bit integer, we need to perform a compression function on the hash code. If keys are short non-negative integers, the compression function itself is the hash function.

### The division method
The **division method** for creating functions maps a key (hash code) *k* into one of *m* slots by taking the remainder of *k* divided by *m*. That is, the compression function is:

\\[h(k) = k \ mod \ m\\]

Note that *m* should be a prime. Otherwise, it may be the case that not all the bits of the key play a role, which amounts to missing an opportunity to disperse the values evenly. For example, if the keys are base-10 numbers and *m* is \\(10^k\\), then only the *k* least significant digits are used.

### The multiplication method
The general **multiplication method** for creating compression function operates in two steps:

1. Multiply the key (hash code) *k* by a constant *A* in the range \\(0 < A < 1\\), and extract the fractional part of *kA*
2. Multiply this value by *m* and take the floor of the result

That is, the compression function is

\\[h(k) = \lfloor m(kA \ mod \ 1)\rfloor\\]

The general multiplication method has the advantage that the value of
*m* is not critical and you can choose it independently of how you choose the multiplicative constant *A*.

### The multiply-shift method
In practice, the multiplication method is best in the special case where the number *m* of hash-table slots is an exact power of 2, so that \\(m = 2^{\ell}\\) for some integer \\(\ell\\). If you choose a fixed *w*-bit positive integer \\(a = A2^{w}\\), where *0 < A < 1* so that *a* is in the range \\(0 < a < 2^{w}\\). You can define the hash function

\\[h(k) = (ka \ mod \ 2^{w}) >>> (w - \ell)\\]

---
[^mask] We omitted the *mask code* here for simplicity. The real code inside **for** is `h = 31 * h + (v & 0xff)`. The use of a small prime integer such as 31 ensures that the bits of all the characters play a role. 