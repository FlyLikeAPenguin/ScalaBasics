package com.flylikeapenguin

object ContextualAbstractions {

  /*
  1 - Context parameters/arguments
  */

  val aList = List(2, 1, 3, 4)
  val anOrderedList = aList.sorted // contextual argument: (ordering))

  // Ordering
  given descendingOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _) // (a,b) => a > b
  // analogous to an implicit val

  trait Combinator[A] {
    def combine(x: A, y: A): A
  }

  // using statement takes a given if one is available
  def combineAll[A](list: List[A])(using combinator: Combinator[A]): A =
    list.reduce((a,b) => combinator.combine(a, b))

  given intCombinator: Combinator[Int] = new Combinator[Int]{
    override def combine(x: Int, y: Int): Int = x + y
  }

  val theSum = combineAll(aList) // intCombinator is passed automatically

  /*
    Given places
      - local scope
      - imported  - import yourpackage.given
      - the companions of all the types involved in the method call
        - the companion of List
        - the companion of Int
  */

  // context bounds
  def combineAll_v2[A](list: List[A])(using Combinator[A]): A = ???
  def combineAll_V3[A : Combinator](list: List[A]): A = ??? // Read as "A" MUST have a Combinator in scope

  /*
  Where context args are useful
  - type classes
  - dependency injection
  - context dependent functionality
  - type-level programming
  */

  /*
  2 - extension methods
  */

  case class Person(name: String){
    def greet(): String = s"Hi, my name is $name"
  }

  extension (string: String)
    def greet(): String = new Person(string).greet()

  val joeGreeting = "Joe".greet() // "type enrichment"

  // Power
  extension [A] (list: List[A])
    def combineAllValues(using combinator: Combinator[A]): A =
      list.reduce(combinator.combine)

  val theSum_v2 = aList.combineAllValues

  def main(args: Array[String]): Unit = {
    println(anOrderedList)
    print(theSum)
  }
}
