package com.ruten.service
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ExceptionHandler, Route}
import com.ruten.util.Tools
import com.ruten.db.DBAcess
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}
import com.github.blemale.scaffeine.{ Cache, Scaffeine }
import scala.concurrent.duration._



trait BerkerlyDbService extends DBAcess with Tools{
   
  implicit val system :ActorSystem

  implicit val materializer :ActorMaterializer

  //val logger = Logging(system, getClass)

  implicit def myExceptionHandler =
    ExceptionHandler {
      case e: ArithmeticException =>
        extractUri { uri =>
          complete(HttpResponse(StatusCodes.InternalServerError, entity = s"Data is not persisted and something went wrong"))
        }
    }

  implicit val cache: Cache[String, String]
  
  val berkerlyRoutes : Route = Routing(cache,system)
  
}


class runBerkerlyDbService(implicit val system: ActorSystem, implicit val materializer : ActorMaterializer, implicit val cache: Cache[String, String]) extends BerkerlyDbService{

  def startServer(addredss: String, port: Int) = {

    Http().bindAndHandle(berkerlyRoutes, addredss, port)
  }

}
