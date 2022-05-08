package com.flylikeapenguin

object Basics extends App {

  // defining a value
  val meaningOfLife: Int = 42 // Constant
  val aBoolean = false // Compiler can infer the type automatically
  val aString = "I Love Scale"
  val aComposedString = "I" + " " + "Love" + " " + "Scala"
  val anInterpolatedString = s"The meaning of life is $meaningOfLife"

  // expressions = structures that can be reduced to a value
  val anExpression = 2 + 3

  // If-expression
  val IfExpression = if (meaningOfLife > 43) 56 else 999 // Akin to meaningOfLife > 43 ? 56 : 999
  val chainedIfExpression =
    if (meaningOfLife > 43) 56
    else if (meaningOfLife < 0) -2
    else if (meaningOfLife > 999) 78
    else 0

  // code blocks
  // Automatically infers type of code block at compile time
  val aCodeBlock = {
    // definitions
    val aLocalValue = 67

    // Automatically returns the last expression of a code block
    aLocalValue + 3
  }

  // functions
  def myFunction(x: Int, y: String): String = {
    y + " " + x
  }

  // recursive functions
  // In Scala we don't use loops or iteration, we use recursion.
  def factorial(n: Int): Int = {
    if (n <= 1) 1
    else n * factorial(n - 1)
  }

  // the Unit type = no meaningful value == "void" in other languages
  // aka side effects
  println("I love scala")
  println(factorial(5))

  def myUnitReturningFunction(): Unit = {
    println("I don't love returning Unit")
  }

  val theUnit = ()
}
