package com.ruten.service
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.ruten.db._
import com.ruten.service._
import com.ruten.setting._
import com.ruten.util._
import scala.concurrent.duration._
import com.github.blemale.scaffeine.{ Cache, Scaffeine }
//import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.StdIn

object StartBerkerlyServer extends App with Tools{
  
  // used to run the actors
  implicit val system: ActorSystem = ActorSystem("my-system")
  // materializes underlying flow definition into a set of actors
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher
  //implicit val cache: Cache[String] = LruCache(maxCapacity = 100, timeToLive = Duration(30, MINUTES))


  implicit val cache: Cache[String, String] =
      Scaffeine()
        .recordStats()
        .expireAfterWrite(10.second)
        .maximumSize(500)
        .build[String, String]()

  

  val port = (new LoadConfSetting("ip")).getConfigData("port").toInt

  val server = new runBerkerlyDbService()

  val bindingFuture = server.startServer(getLocalIP,port)

  println(s"Server online at http://$getLocalIP:$port/")
  StdIn.readLine() // let it run until user presses return
  
  /*
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port 
      .onComplete(_ => system.terminate()) // and shutdown when done
    */
    // bind failed
    bindingFuture
    .onFailure {
      case ex: Exception =>{
      println(s"Failed to bind to $getLocalIP:$port")
      system.terminate()
    }
      //log.error(ex, "Failed to bind to {}:{}!", host, port)
    }
}
