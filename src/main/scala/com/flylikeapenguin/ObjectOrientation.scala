package com.flylikeapenguin

// extending app gives a default main method for free to allow the app to be run
object ObjectOrientation extends App {

  // class and instance
  class Animal {
    // define fields
    val age: Int = 0
    def eat() = println("I'm Eating")
  }
  val AnAnimal = new Animal

  // inheritance

  class Dog(val name: String) extends Animal // Class definition is also the constructor definition
  val aDog = new Dog("Lassie")

  // constructor arguments are NOT fields: need to pul val before the argument to make it accessible later
  aDog.name

  // subtype polymorphism
  val aDeclaredAnimal: Animal = new Dog("Hachi")
  aDeclaredAnimal.eat() // the most derived method will be called at runtime

  // abstract class
  abstract class WalkingAnimal {
    // all methods and fields are public by default. Also supports private and protected.
    val hasLegs = true
    // will need an inheriting class to implement the method
    def walk(): Unit
  }

  // "interface" = ultimate abstract type
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait Philosopher {
    def ?!(thought: String): Unit // valid method name. Scala is very permissive with method naming
  }

  // single-class inheritance, multi-trait "mixing"
  class Crocodile extends Animal with Carnivore with Philosopher {
    override def eat(animal: Animal): Unit = println("I am eating an animal")

    override def ?!(thought: String): Unit = println(s"I was thinking: $thought")
  }

  val aCroc = new Crocodile
  aCroc.eat(aDog)
  aCroc eat aDog // infix notation = object-method-argument, only available for methods with one argument
  aCroc ?! "What if we could fly?"

  // operators are actually methods
  val basicMath = 1 + 2
  val anotherBasicMath = 1.+(2) // equivalent

  // anonymous classes
  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("I am a dinosaur so I can eat almost anything")
  }
  /*
    compiler effectively does:

    class Carnivore_Anonymous_12345 extends Carnivore {
      override def eat(animal: Animal): Unit = println("I am a dinosaur so I can eat almost anything")
    }

    val dinosaur = new Carnivore_Anonymous_12345
   */

  // singleton object

  object MySingleton { // the only instance of the MySingleton type
    val mySpecialValue = 54363
    def mySpecialMethod(): Int = 5327
    def apply(x: Int): Int = x + 1
  }

  MySingleton.mySpecialMethod()
  // These two are equivalent. Apply is a special word.
  MySingleton.apply(65)
  MySingleton(65)

  object Animal { // When you have a singleton and a class in the same file, they are called companion objects
    // companions can access each other's private fields/methods
    // singleton Animal and instances of Animal are different things
    val canLiveForever = false // Scala version of "static" fields/methods
  }

  val animalsCanLiveForever = Animal.canLiveForever // Scala version of "static" fields/methods


  /*
  case classes = lightweight data structures with some boilerplate already done for you
  - sensible equals and hash code are create automatically
  - sensible and quick serialization
  - companion with apply
  - pattern matching
   */
  case class Person(name: String, age: Int)

  // may be constructed without new
  val bob = Person("Bob", 54) // equivalent to Person.apply("Bob", 54)

  // exceptions
  try {
    // code that can throw
    val x: String = null
    x.length
  } catch {
    case e: Exception => "Some faulty error message"
  } finally {
    // executes no matter what
  }

  // generics
  abstract class MyList[T] {
    def head: T
    def tail: MyList[T]
  }

  // using a generic with a concrete type
  val aList: List[Int] = List(1, 2, 3) // List.apply(1, 2, 3)
  val first = aList.head // int
  val rest = aList.tail

  val aStringList = List("hello", "Scala")
  val firstString = aStringList.head // string

  // Point #1: In scala we usually operate with Immutable Values/Objects
  // Any modification to a object should return another object
  //    Benefit 1: Works miracles in multithreaded/distributed environments
  //    Benefit 2: Helps making sense of the code ("reasoning about")
  val reversedList = aList.reverse // returns a new List

  // Point #2: Scala is closest to the OOP ideal
}

