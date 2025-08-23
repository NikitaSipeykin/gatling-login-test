import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class LoginSimulation extends Simulation {

  val httpProtocol = http.baseUrl("http://testsite.com")

  // Читаем данные из CSV
  val feeder = csv("users.csv").circular

  val scn = scenario("Login Scenario")
    .feed(feeder)
    .exec(http("Open Home").get("/"))
    .pause(1)
    .exec(
      http("Login")
        .post("/login")
        .formParam("username", "${username}")
        .formParam("password", "${password}")
    )
    .pause(1)
    .exec(http("Profile").get("/profile"))

  setUp(
    scn.inject(rampUsers(10).during(5.seconds))
  ).protocols(httpProtocol)
}