import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class UC01_FindMax extends Simulation {

  val httpProtocol = http
    .baseUrl("https://BASE_URL") // укажи реальный URL
    .acceptHeader("application/json")

  val csvFeeder = csv("new.csv").circular

  val scn = scenario("UC01_FindMax")
    .feed(csvFeeder)
    .foreach(Seq("limit"), "n") {
      exec { session =>
        val rawLimit = session("limit").as[String].trim
        val limit = try { rawLimit.toInt } catch { case _: NumberFormatException => 0 }
        if (limit > 0)
          session.set("parsedLimit", limit)
        else
          session.markAsFailed
      }
    }
    .exec(
      http("FindMaxRequest")
        .get("${path}")
        .queryParam("guest_id", "${guest_id}")
        .queryParam("guest_id_type", "${guest_id_type}")
        .queryParam("business_unit", "${business_unit}")
        .queryParam("model_type", "${model_type}")
        .queryParam("limit", "${parsedLimit}")
    )
    .pause(1)

  setUp(
    scn.inject(
      rampUsers(1000) during (10.minutes),
      constantUsersPerSec(1000) during (10.minutes),
      rampUsers(200) during (2.minutes),
      constantUsersPerSec(1200) during (10.minutes),
      rampUsers(200) during (2.minutes),
      constantUsersPerSec(1400) during (10.minutes),
      rampUsers(200) during (2.minutes),
      constantUsersPerSec(1600) during (10.minutes),
      rampUsers(200) during (2.minutes),
      constantUsersPerSec(1800) during (10.minutes),
      rampUsers(200) during (2.minutes),
      constantUsersPerSec(2000) during (10.minutes),
      rampUsers(200) during (2.minutes),
      constantUsersPerSec(2200) during (10.minutes),
      rampUsers(200) during (2.minutes),
      constantUsersPerSec(2400) during (10.minutes),
      rampUsers(200) during (2.minutes),
      constantUsersPerSec(2600) during (10.minutes),
      rampUsers(300) during (2.minutes),
      constantUsersPerSec(2900) during (10.minutes),
      rampUsers(100) during (2.minutes),
      constantUsersPerSec(3000) during (10.minutes),
      rampUsersPerSec(3000) to 0 during (20.seconds)
    )
  ).protocols(httpProtocol)
}
