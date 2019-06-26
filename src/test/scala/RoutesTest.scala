import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}

class RoutesTest extends WordSpec with Matchers with ScalatestRouteTest {

  "Routes for command/assembly" must {
    "example route" in {
      Get("/example") ~> Routes.route ~> check {
        status shouldBe StatusCodes.GatewayTimeout
      }
    }
  }
}
