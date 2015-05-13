import org.scalatest.{Matchers, FlatSpec}

class TrainingSpec extends FlatSpec with Matchers {

  "Training" should "be a lot of fun" in {
    "functional programming training" should include("fun")
  }
}
