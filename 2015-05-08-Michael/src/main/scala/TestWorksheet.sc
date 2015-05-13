import java.lang.NumberFormatException

import scala.util.matching.Regex

implicit class Regex(sc: StringContext) {
  def r = new util.matching.Regex(sc.parts.mkString, sc.parts.tail.map(_ => "x"): _*)
}

"1 plus 2 plus 3" match {
  case r"(([0-9]+)${list} [a-zA-Z ]*)+ ([0-9]+)${end}" => {
    list.map(_.toString) :: end :: Nil
}
  case _ => "foobar"
}

abstract class Term {
  val isNumber: Boolean;
  val getNumber: Long;
}

case object NullTerm extends Term {
  override val isNumber: Boolean = false
  override val getNumber: Long = 0
}

case class TermPart(a: Long, operator: String) extends Term {
  override val isNumber = false
  override val getNumber: Long = { throw new IllegalAccessException("...")}
  def calc(b: Long) : Long = {
    operator match {
      case "+" => a + b
      case "-" => a - b
      case "*" => a * b
      case "/" => if (b==0)  0 else a/b
      case "**" => Math.pow(a, b).toLong
      case _ => throw new IllegalArgumentException("...")
    }
  }
}

object TermPart {
  def apply(s: String) = {
    s.split(" ").toList match {
      case number :: operator :: Nil => new TermPart(number.toLong, operator)
      case _ => throw new IllegalArgumentException("Illegal: " + s)
    }
  }
}

case class TermNumber(a: Long)

object TermNumber {
  def apply(s: String) = new TermNumber(s.toLong)
}

def isNumber(s: String) = {
  try {
    s.toLong
    true
  } catch {
    _ => false
  }
}

"1 plus 2 plus 3".split(" ").foldLeft( (a,b) =>
  if (! isNumber(a))
    TermPart(a,b)
else
)



"(([0-9]+) [a-zA-Z ]*)+ ([0-9]+)".r.replaceAllIn("1 plus 2 plus 3", "test")


val factory = new javax.script.ScriptEngineManager

val engine = factory.getEngineByName("JavaScript")

engine.eval("return 'Hello, World\\n'")

