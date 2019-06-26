import akka.http.scaladsl.marshalling.sse.EventStreamMarshalling._
import akka.http.scaladsl.marshalling.sse.EventStreamMarshalling._
import akka.http.scaladsl.marshalling.sse.EventStreamMarshalling._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.model.sse.ServerSentEvent
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ExceptionHandler, Route}
import akka.stream.scaladsl.Source

import scala.concurrent.{Future, TimeoutException}

object Routes {

  implicit def handlers: ExceptionHandler = ExceptionHandler {
    case ex: TimeoutException ⇒
      complete(StatusCodes.GatewayTimeout, s"Our message: ${ex.getMessage}")
  }

  def route: Route =
    handleExceptions(handlers) {
      get {
        path("example") {
          onSuccess(Future.failed[Source[ServerSentEvent, akka.NotUsed]](new TimeoutException())
          ) {
            source => complete(source)
          }
        }
      }
    }
}
