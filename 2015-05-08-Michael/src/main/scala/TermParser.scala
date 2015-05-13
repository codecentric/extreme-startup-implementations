
import scala.util.parsing.combinator._

class TermParser extends JavaTokenParsers {

  type Operator = (Long,Long) => (Long)

  def num: Parser[Long] = floatingPointNumber ^^ (_.toLong)

  def operator: Parser[Operator] = ("plus" | "minus" | "multiplied by" | "to the power of") ^^ {
    case "plus" => (x, y) => x + y
    case "minus" => (x, y) => x - y
    case "multiplied by" => (x, y) => x * y
    case "to the power of" => (x, y) => Math.pow(x,y).toLong
  }

  def simpleTerm: Parser[Long] = (num ~ operator ~ num) ^^ {
    case (a ~ op ~ b) => op(a,b)
  }

  def combinedTerm: Parser[Long] = rep(num ~ operator) ~ num ^^ {
    case ((firstNum ~ firstOp) :: list) ~ num => list.foldLeft[PartialTerm](PartialTerm(firstNum, firstOp)) { (partialTerm, nextTerm) =>
      nextTerm match {
        case num ~ op => PartialTerm(partialTerm.op(partialTerm.num, num), op)
      }
    } match {
      case PartialTerm(latestNum, op) => op(latestNum,num)
    }
  }

  case class PartialTerm(num: Long, op: Operator)

  def parseAndCalculate(s: String): Long = {
    return parseAll(combinedTerm, s).get
  }

}
