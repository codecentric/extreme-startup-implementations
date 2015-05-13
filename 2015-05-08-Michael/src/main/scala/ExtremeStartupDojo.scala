import akka.actor.ActorSystem
import akka.event.{LoggingAdapter, Logging}
import akka.http.scaladsl.Http
import akka.http.scaladsl.client.RequestBuilding
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.model.{HttpResponse, HttpRequest}
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.directives.{LoggingMagnet, DebuggingDirectives, LogEntry}
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.{ActorFlowMaterializer, FlowMaterializer}
import akka.stream.scaladsl.{Flow, Sink, Source}
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import java.io.IOException
import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.math._
import spray.json.DefaultJsonProtocol

trait Service {
  implicit val system: ActorSystem

  implicit def executor: ExecutionContextExecutor

  implicit val materializer: FlowMaterializer

  def config: Config

  val termParser = new TermParser();

  val logger: LoggingAdapter
  implicit class Regex(sc: StringContext) {
    def r = new util.matching.Regex(sc.parts.mkString, sc.parts.tail.map(_ => "x"): _*)
  }

  val routes = {
    logRequestResult("extreme-startup-dojo") {
          pathPrefix("sum") {
            (post & entity(as[String])) { content =>
              val foobar = content.split(" ")
              complete {
                OK -> Calculator.calculate(content).toString
              }
            }
          } ~ (get & parameter("q".as[String])) { query =>
          complete {
            query.split(":").toList match {
              case List(_," which of the following numbers is the largest",numbers) => Calculator.max(numbers)
              case _ :: " which of the following numbers is both a square and a cube" :: numbers :: Nil => Calculator.findSquareAndRoot(numbers)
              case _ :: " which of the following numbers are primes" :: numbers :: Nil => Calculator.findPrime(numbers)
              case _ :: "what is your name" :: Nil => OK -> "Michael"
              case _ :: question :: Nil => {
                println(question)
                question match {
                  case r" what is ([0-9]+)${first} plus ([0-9]+)${second}" => OK -> Calculator.add(first, second)
                  case r" what is ([0-9]+)${first} minus ([0-9]+)${second}" => OK -> Calculator.minus(first, second)
                  case r" what is ([0-9]+)${first} plus ([0-9]+)${second} plus ([0-9]+)${third}" => OK -> Calculator.add(Calculator.add(first, second), third)
                  case r" what is ([0-9]+)${first} to the power of ([0-9]+)${second}" => OK -> Calculator.pow(first, second)
                  case r" what is ([0-9]+)${first} multiplied by ([0-9]+)${second}" => OK -> Calculator.mult(first, second)
                  case r" what is ([0-9]+.*)${term}" => OK -> termParser.parseAndCalculate(term).toString
                  case r" what is the ([0-9]+)${n}th number in the Fibonacci sequence" => OK -> Calculator.fib(n).toString
                  case " which city is the Eiffel tower in" => OK -> "Paris"
                  case " what currency did Spain use before the Euro" => OK -> "Peseta"
                  case " what colour is a banana" => OK -> "yellow"
                  case " who is the Prime Minister of Great Britain" => OK -> "David Cameron"
                  case _ => BadRequest -> "Did not understand"
                }
              }
              case _ => BadRequest -> "Did not understand"
            }
          }
        }
    }
  }
}

object ExtremeStartupDojo extends App with Service {
  override implicit val system = ActorSystem()
  override implicit val executor = system.dispatcher
  override implicit val materializer = ActorFlowMaterializer()

  override val config = ConfigFactory.load()
  override val logger = Logging(system, getClass)

  Http().bindAndHandle(routes, config.getString("http.interface"), config.getInt("http.port"))
}
