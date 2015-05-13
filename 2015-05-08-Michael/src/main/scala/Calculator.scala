import akka.http.scaladsl.marshalling.ToResponseMarshallable

import scala.util.parsing.combinator._

object Calculator {
  def calculate(term: String) : Double = {
    term.split(" ").map(_.trim.toDouble).reduce(_+_)
  }

  def findPrime(s: String): String = {
    toLongList(s).filter(isPrime).map(_.toString).mkString(", ")
  }

  def isPrime(n: Long) = (2l until n).forall(d => n % d != 0)

  def isSquare (x: Long): Boolean = {
    val sqrt: Long = Math.sqrt(x.toDouble).toLong
    return (x == sqrt * sqrt)
  }

  def isCube (x: Long): Boolean = {
    val y: Long = Math.cbrt(x.toDouble).toLong
    return (x == y * y * y)
  }

  def findSquareAndRoot(s: String): String = {
    toLongList(s).filter(isSquare).filter(isCube).map(_.toString).mkString(", ")
  }

  def add(value: String, value1: String): String = {
    (value.toLong + value1.toLong).toString
  }

  def minus(value: String, value1: String): String = {
    (value.toLong - value1.toLong).toString
  }

  def pow(value: String, value1: String): String = {
    Math.pow(value.toLong,value1.toLong).toLong.toString
  }

  def mult(value: String, value1: String): String = {
    (value.toLong * value1.toLong).toString
  }

  def max(term: String): String = {
    toLongList(term).reduce({ (a,b) => if (a>b) a else b }).toString
  }

  def toLongList(term: String): Array[Long] = {
    term.split(",").map(_.trim.toLong)
  }

  def fib( nstring : String) : Int = {
    val n = nstring.toInt
    def fib_tail( n: Int, a:Int, b:Int): Int = n match {
      case 0 => a
      case _ => fib_tail( n-1, b, a+b )
    }
    return fib_tail( n, 0, 1)
  }
}
