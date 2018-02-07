import java.net.URL

import com.greyamigo.URLPinger
import org.scalatest.{Matchers, WordSpec}

import scala.io.Source

class URLPingerSpec extends WordSpec with Matchers {

  "URL Pinger" should {
    "fire 4 URLs and store in DB" in {

      URLPinger.ping4URlsandSaveToDB(
        List(
          new URL("https://google1.com"),
          new URL("https://google2.com"),
          new URL("https://google3.com"),
          new URL("https://test.com"))
      )
      Source.fromFile("./DB.txt").getLines.toList.size % 4 shouldBe 0
    }
    "find jsession ids" in {
      URLPinger.findJSessionID().headOption match {
        case None => true
        case Some(str) => str.contains("JSESSIONID")
      }
    }
  }
}
