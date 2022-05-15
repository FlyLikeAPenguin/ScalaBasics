package com.flylikeapenguin

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

object Advanced extends App {

  // lazy evaluation
  lazy val aLazyValue = 2
  lazy val lazyValueWithSideEffect = {
    println("I am so very lazy")
    43
  }

  val eagerValue = lazyValueWithSideEffect + 1
  // useful in infinite collections

  // "pseudo-collections": Option, Try
  def methodWhichCanReturnNull(): String = "Hello, Scala"
  val anOption = Option(methodWhichCanReturnNull()) // Some("Hello, Scala")
  // option = "collection" which contains at most one element: Some(value), or None

  val stringProcessing = anOption match {
    case Some(string) => s"I have a valid string: $string"
    case None => "I obtained nothing"
  }
  // map, flatMap, filter also apply to option

  def methodWhichCanThrowException(): String = throw new RuntimeException
  val aTry = Try(methodWhichCanThrowException())
  // a try = "collection" with either value if the code went well, or an exception if the code threw one

  val anotherStringProcessing = aTry match {
    case Success(validValue) => s"I have obtained a valid string: $validValue"
    case Failure(exception) => s"Exception: $exception"
  }
  // map, flatMap, filter also apply to Try

  // How to evaluate something on another thread aka async programming
  val aFuture = Future { // Future.apply()
    println("Loading...")
    Thread.sleep(1000)
    println("I have computed a value")
    67
  }
  // future is a "collection" which contains a value when it is evaluated
  // future is composable with map, flatMap, and filter
  // monads


  // Implicits basics
  // #1: Implicit arguments
  def aMethodWithImplicitArgs(implicit arg: Int) = arg + 1
  implicit val myImplicitInt: Int = 46
  println(aMethodWithImplicitArgs) // aMethodWithImplicitArgs(myImplicitInt) : 47

  // #2: implicit conversions
  implicit class MyRichInteger(n: Int) {
    def isEven(): Boolean = n % 2 == 0
  }

  println(23.isEven()) // Implicit is like an extension method in C#
  // Use with care
}
