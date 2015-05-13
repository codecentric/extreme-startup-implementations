import org.scalatest.{Matchers, FlatSpec}

class CalculatorSpec extends FlatSpec with Matchers {
  "Calculator" should "sum all elements" in {
    Calculator.calculate("2 5.7") shouldEqual 7.7
  }

  it should "find max number" in {
    Calculator.max("3, 5, 6, 2") shouldEqual("6")
  }

  it should "check cubes" in {
    Calculator.isCube(27) shouldBe true
    Calculator.isCube(26) shouldBe false
    Calculator.isCube(28) shouldBe false
  }

  it should "check squares" in {
    Calculator.isSquare(121) shouldBe true
    Calculator.isSquare(120) shouldBe false
    Calculator.isSquare(122) shouldBe false
  }
  
  it should "filter squarecube" in {
    Calculator.findSquareAndRoot("7529536 , 234, 234, 567") shouldEqual "7529536"
  }

  it should "filter squarecube 2" in {
    Calculator.findSquareAndRoot("7529536 ,7529536, 234, 234, 567") shouldEqual "7529536, 7529536"
  }
}
