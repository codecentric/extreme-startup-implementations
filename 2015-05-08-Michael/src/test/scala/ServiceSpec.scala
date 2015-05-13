import akka.event.NoLogging
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.ContentTypes._
import akka.http.scaladsl.model.{HttpResponse, HttpRequest}
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.stream.scaladsl.Flow
import org.scalatest._

class ServiceSpec extends FlatSpec with Matchers with ScalatestRouteTest with Service {
  override def testConfigSource = "akka.loglevel = WARNING"
  override def config = testConfig
  override val logger = NoLogging

  it should "sum all numbers" in {
    Post(s"/sum", "3.1 7.3") ~> routes ~> check {
      status shouldBe OK
      responseAs[String] shouldEqual "10.4"
    }
  }

  it should "find max number" in {
    Get(s"/?q=test:+which+of+the+following+numbers+is+the+largest:+5,+2,+7,+9") ~> routes ~> check {
      status shouldBe OK
      responseAs[String] shouldEqual "9"
    }
  }

  it should "add numbers" in {
    Get(s"/?q=test:+what+is+11+plus+2") ~> routes ~> check {
      status shouldBe OK
      responseAs[String] shouldEqual "13"
    }
  }

  it should "subtract numbers" in {
    Get(s"/?q=test:+what+is+11+minus+2") ~> routes ~> check {
      status shouldBe OK
      responseAs[String] shouldEqual "9"
    }
  }

  it should "multiply numbers" in {
    Get(s"/?q=test:+what+is+11+multiplied+by+2") ~> routes ~> check {
      status shouldBe OK
      responseAs[String] shouldEqual "22"
    }
  }

  it should "find prime" in {
    Get(s"?q=5cf19050:+which+of+the+following+numbers+are+primes:+773,+293") ~> routes ~> check {
      status shouldBe OK
      responseAs[String] shouldEqual "773, 293"
    }
  }



  it should "find currency of spain" in {
    Get(s"?q=5cf19050:+what+currency+did+Spain+use+before+the+Euro") ~> routes ~> check {
      status shouldBe OK
      responseAs[String] shouldEqual "Peseta"
    }
  }


  it should "return fibonacci" in {
    Get(s"?q=5cf19050:+what+is+the+9th+number+in+the+Fibonacci+sequence") ~> routes ~> check {
      status shouldBe OK
      responseAs[String] shouldEqual "34"
    }
  }

  it should "return pow" in {
    Get(s"?q=5cf19050:+what+is+3+to+the+power+of+16") ~> routes ~> check {
      status shouldBe OK
      responseAs[String] shouldEqual "43046721"
    }
  }


  it should "return calculate complex equation" in {
    Get(s"?q=5cf19150:+what+is+15+minus+7+plus+2+to+the+power+of+3") ~> routes ~> check {
      status shouldBe OK
      responseAs[String] shouldEqual "1000"
    }
  }

  it should "find eiffeltower" in {
    Get(s"?q=5cf19050:+which+of+the+following+numbers+are+primes:+773,+293") ~> routes ~> check {
      status shouldBe OK
      responseAs[String] shouldEqual "773, 293"
    }
  }
}
