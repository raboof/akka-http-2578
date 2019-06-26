import akka.actor._
import akka.stream._
import akka.http.scaladsl._

object Main extends App {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  Http().bindAndHandle(Routes.route, "localhost", 8080)
}
