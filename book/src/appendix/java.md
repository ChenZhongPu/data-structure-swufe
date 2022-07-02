# IDEA for Java
In this book, we use JDK 11, and the IntelliJ IDEA Community[^version] is enough for our course.

## Create an empty project without building system

For the simplicity, we don't need to rely on third party libraries in most cases when learning data structures, so you can choose *IntelliJ* for `Build system`:

<img src="img/idea.png">

## Create an empty project with `gradle`
In order to integrate [JUnit 5](https://junit.org/junit5/) with an ease in our project, `gradle` is preferred as the *Build System* in this book.

<img src="img/gradle.png">

And you can customize the *GroupID* if you like. In `build.gradle`, we can find that the *dependencies* with respect to units test have been specified:

``` groovy
dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.8.1'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.8.1'
}
```

---
[^version] The Ultimate version (*paid*) offers more features than the Community one (*free*).