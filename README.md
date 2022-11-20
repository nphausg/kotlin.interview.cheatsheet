<h1 align="center"> Kotlin Interview Cheatsheet </h1>

<p align="center">
  <a href="https://github.com/facebook/react-native/blob/HEAD/LICENSE">
    <img src="https://img.shields.io/badge/license-MIT-blue.svg" alt="React Native is released under the MIT license." />
  </a>
  <a href="https://reactnative.dev/docs/contributing">
    <img src="https://img.shields.io/badge/PRs-welcome-brightgreen.svg" alt="PRs welcome!" />
  </a>
</p>

<div align="center"><img src="cover.png" alt="drawing"/></div>

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/colored.png)

# 🚀 Class & Object
## Override Rules
In Kotlin, implementation inheritance is regulated by the following rule: if a class inherits multiple implementations of the same member from its immediate superclasses, 
it must override this member and provide its own implementation (perhaps, using one of the inherited ones)
See: https://kotlinlang.org/docs/inheritance.html#overriding-rules
```kotlin

interface Drivable {
    fun drive() {
        println("Driving {interface}")
    }
}

abstract class Vehicle {
    open fun drive() {
        println("Driving")
    }
}

class Sedan : Vehicle(), Drivable {
    override fun drive() {
        super<Drivable>.drive()
        super<Vehicle>.drive()
    }
}
```

## Interfaces Inheritance
An interface can derive from other interfaces, meaning it can both provide implementations for their members and declare new functions and properties.
Quite naturally, classes implementing such an interface are only required to define the missing implementations. 
See: https://kotlinlang.org/docs/interfaces.html#interfaces-inheritance
```kotlin
interface Named {
    val name: String
}

interface Person : Named {
    val firstName: String
    val lastName: String

    override val name: String get() = "$firstName $lastName"
}

data class Employee(
    // implementing 'name' is not required
    override val firstName: String,
    override val lastName: String,
    val position: Position
) : Person
```
## Functional (SAM) interfaces
An interface with only one abstract method is called a functional interface, or a Single Abstract Method (SAM) interface.
The functional interface can have several non-abstract members but only one abstract member.
See: https://kotlinlang.org/docs/fun-interfaces.html
```kotlin
fun interface IntPredicate {
    fun accept(i: Int): Boolean
}
// Creating an instance of a class
val isEven = object : IntPredicate {
    override fun accept(i: Int): Boolean {
        return i % 2 == 0
    }
}
// By leveraging Kotlin's SAM conversion, you can write the following equivalent code instead:
val isEven = IntPredicate { i -> i % 2 == 0 }
```
## Generics: in, out, where
Let's think about why Java needs these mysterious wildcards.
The problem is explained well in Effective Java, 3rd Edition, Item 31: Use bounded wildcards to increase API flexibility.
First, generic types in Java are invariant, meaning that List<String> is not a subtype of List<Object>.
If List were not invariant, it would have been no better than Java's arrays, as the following code would have compiled but caused an exception at runtime:
See: https://kotlinlang.org/docs/generics.html
```java
// Java
List<String> strs = new ArrayList<String>();
List<Object> objs = strs; // !!! A compile-time error here saves us from a runtime exception later.
objs.add(1); // Put an Integer into a list of Strings
String s = strs.get(0); // !!! ClassCastException: Cannot cast Integer to String
```
In Java, you probably learned this the hard way, see Effective Java, 3rd Edition, Item 28: Prefer lists to arrays)
That's why the actual signature of addAll() is the following:

```java
// Java
interface Collection<E> ... {
    void addAll(Collection<? extends E> items);
}
```

```kotlin
// Kotlin
class Source<out T>(private val t: T) {
    fun produceT(): T {
        return t
    }
}

class Sink<in T> {
    fun consume(t: T) {

    }
}

fun main(args: Array<String>) {
    val strSource: Source<String> = Source("Producer")
    val anySource: Source<Any> = strSource  // out-> covariance
    anySource.produceT()

    val anySink: Sink<Any> = Sink()
    val strSink: Sink<String> = anySink     // in -> contravariance
    strSink.consume("Consumer")
}
```
## ✨ Contributing

Please feel free to contact me or make a pull request.

## ⚙️ Copyright

```
Created by $username on $today
Copyright (c) $today.year . All rights reserved.
Last modified $file.lastModified
```

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/colored.png)

## 👇 Authors

<p>
    <a href="https://nphausg.medium.com/" target="_blank">
    <img src="https://avatars2.githubusercontent.com/u/13111806?s=400&u=f09b6160dbbe2b7eeae0aeb0ab4efac0caad57d7&v=4" width="96" height="96">
    </a>
</p>
