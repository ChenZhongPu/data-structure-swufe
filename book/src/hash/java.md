# Hash in Java
You can override the `hashCode()` method for your class. By default, this is typically implemented by converting the internal address of the object into an integer, but this implementation technique is not required by the Java. There are two basic rules when implementing `hashCode()`:

- You must override `hashCode` in every class that overrides `equals`.
- Equal objects must have equal hash codes.

Collections such as [HashMap](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/HashMap.html) and [HashSet](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/HashSet.html) rely on the hash code of an object. The following is the worst possible implementation:

```java
public class Book {
    private String title;
    private double price;
    ...
    @Override // never do this
    public int hashCode() {
       return 42;
    }
}
```

In this case, every object hashes to the same bucket, and hash tables degenerate to linked lists.

## Hash code
Java has already provided built-in feasible implementations for common data types, and it is recommended using the corresponding static methods. For example, `Float.hashCode(3.14f)`. 

Furthermore, if all fields in your class have their well-defined hash code implementations, then you can simply use the `hash()` method from `Objects`, which generates a hash code for a sequence of input values. 

```java
@Override
public int hashCode() {
    return Objects.hash(title, price);
}
```

To design an array index, we shall also mark off the sign bit to turn the 32-bit number into a 31-bit non-negative integer. Take the modular hashing for instance:

```java
private int hash(Object x) {
    return (x.hashCode() & 0x7fffffff) % m;
}
```

## A good hash function
In summary, we have three primary requirements in implementing a good hash function for a given data type:

- It should be **consistent**: equal keys must produce the same hash value
- It should be **efficient** to compute
- It should **uniformly distribute the keys**

Satisfying these requirements simultaneously is a job for experts. Java Programmers can assume `hashCode()` does the job.