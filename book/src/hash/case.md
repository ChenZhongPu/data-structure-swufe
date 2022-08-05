# Case Study
Hash functions are highly related with computer security (e.g., cryptography). For example, when you want to download some file from the Internet, you will probably notice a magic string (e.g., `sha512`) which can be used to verify the integrity of the file. 

In fact, such string is computed by a hash function based on the bits of a file. If the results are matched, we can say the file is not changed by anyone due to the consistency of a hash function.

- Java's [MessageDigest](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/security/MessageDigest.html) provides many cryptographic hash functions to find hash value of a text.
- Python's [hashlib](https://docs.python.org/3/library/hashlib.html) implements a common interface to many different secure hash and message digest algorithms.

But detailed discussion on cryptography is out of the scope of this book. 

## Hash vs. RBT
Is hashing faster than searching in red-black BSTs?

It depends on the type of the key, which determines the cost of computing *hash code* versus the cost of *comparison*. For typical key types, these costs are similar, so hashing will be significantly faster, since it uses only a constant number of operations.

But it is important to remember that this question is moot if you need ordered operations, which are not efficiently supported in hash tables.