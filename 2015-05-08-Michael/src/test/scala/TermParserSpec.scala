import org.scalatest.{FlatSpec, Matchers}

class TermParserSpec extends FlatSpec with Matchers {

  val termParser = new TermParser()

  it should "parse term 1" in {
    termParser.parseAndCalculate("1 plus 4 multiplied by 2") shouldEqual 10
  }


  it should "parse term 2" in {
    termParser.parseAndCalculate("6 minus 4 to the power of 4") shouldEqual 16
  }
}
