package com.flylikeapenguin

object FunctionalProgramming extends App {

  class Person(name: String) {
    def apply(age: Int): Unit = println(s"I have aged $age years")
  }
  val bob = new Person("Bob")
  bob.apply(43)
  bob(43) // Invoking bob as a function == bob.apply(43)

  /*
  Scala runs on the JVM
  Functional Programming:
  - Compose functions
  - Pass functions as arguments
  - Return functions as results

  Conclusion: FunctionX = Function1, Function2, ... Function22
   */

  val simpleIncrementer = new Function1[Int, Int] {
    override def apply(arg: Int): Int = arg + 1
  }

  simpleIncrementer.apply(23) // 24
  simpleIncrementer(23) // same as calling the apply method
  // Defined like a function and nothing else
  // All scala functions are instances of FunctionX types

  val stringConcatenator = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  stringConcatenator("I love", " Scala") // "I love scala"


  // Syntax sugars
  val doubler: Int => Int = (x: Int) => 2 * x
  doubler(4) // 8
  /*
  Equivalent to:
    val doubler = new Function1[Int, Int] {
    override def apply(arg: Int): Int = 2 * arg
  }
   */


  // higher-order functions take functions as args/return functions as results

  val aMappedList: List[Int] = List(1, 2, 3).map(x => x + 1) // HOF
  println(aMappedList) // List (2, 3, 4)

  val aFlatMappedList = List(1,2,3).flatMap(x => List(x, 2*x))
  println(aFlatMappedList) // List(1, 2, 2, 4, 3, 6)

  val aFilteredList = List(1,2,3,4,5).filter(x => x <= 3)
  println(aFilteredList) // List(1, 2, 3)

  /*
  Shorter syntax
  val aFilteredList = List(1,2,3,4,5).filter(_ <= 3)
   */

  // all pairs between the numbers 1,2,3 and letters 'a', 'b', 'c'
  val allPairs = List(1,2,3).flatMap(number => List('a', 'b', 'c').map(letter => s"$number$letter")) // List(1a, 1b, 1c, 2a, 2b, 2c, 3a, 3b, 3c)
  println(allPairs)

  // for comprehensions
  val alternativePairs = for {
    number <- List(1,2,3)
    letter <- List('a', 'b', 'c')
  } yield s"$number$letter"
  // equivalent to the map/flatmap chain above

  /*
    Collections
   */

  // Lists
  val aList = List(1,2,3,4,5)
  val firstElement = aList.head
  val rest = aList.tail
  val aPrependedList = 0 :: aList // List(0,1,2,3,4,5)
  val anExtendedList = 0 +: aList :+ 6 // List(0,1,2,3,4,5,6)

  //sequences
  val aSequence: Seq[Int] = Seq(1,2,3) // Seq.apply(1,2,3)
  val accessedElement = aSequence(1) // the element at index 1: 2

  // vectors : fast Seq implementation
  val aVector = Vector(1,2,3,4,5)

  // sets = no duplicates
  val aSet = Set(1,2,3,4,1,2,3) // Set(1,2,3,4)
  val setHas5 = aSet.contains(5) // false
  val anAddedSet = aSet + 5 // Set(1,2,3,4,5)
  val aRemovedSet = aSet - 3 // Set(1,2,4)

  // ranges
  val aRange = 1 to 1000
  val twoByTwo = aRange.map(2 * _).toList // List(2,4,6,8...2000)

  // tuples = groups of values under the same value
  val aTuple = ("Bon Jovi", "Rock", 1982)

  // maps
  val phoneBook: Map[String, Int] = Map(
    ("Daniel", 555555),
    "Jane" -> 32425242 // Same as ("Jane", 32425242)
  )

}
