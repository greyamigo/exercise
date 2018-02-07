package com.greyamigo

import java.io.{File, FileOutputStream, PrintWriter}
import java.net.URL

import scala.io.Source
import scala.util.matching.Regex

case class ResponseObject(uri:URL,body:String,responseCode:Int,cookie:Map[String,String])

object db {

  def save(r:ResponseObject) = {
    val pw = new PrintWriter(new FileOutputStream(new File("DB.txt"),true))
    pw.append(s"${r}")
    pw.append("\n")
    pw.close
  }
  def find(attr: String, what: Regex) ={
    Source.fromFile("./DB.txt").getLines.toList
      .filter(_.contains(attr))
      .filter((s)=>what.findFirstMatchIn(s).isDefined)
  }
}

object URLPinger {

 def pingURl(url:URL):ResponseObject ={
   import scala.util.Random
   val body = List("Alexander", "Thor", "Achilles")
   val responseCode = Random.nextInt()
   val cookie = Map("JSESSIONID"->"hi-i-am-a-session-id", "RANDOM"->"Jibberish")
   ResponseObject(url, Random.shuffle(body).head, responseCode, Map(Random.shuffle(cookie).head))
 }

  def ping4URlsandSaveToDB(urls: List[URL]) =
    urls.map((url)=>{
      val response = pingURl(url)
      url.getHost match {
        case "test" => println(response); db.save(response)
        case _      => db.save(response)
      }
    })
  def findJSessionID() = db.find("JSESSIONID","JSESSIONID".r)



}
