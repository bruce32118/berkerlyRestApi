package com.company.main

import akka.actor.ActorSystem
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import scala.concurrent.Future

// json render
import spray.json._

import com.ruten.util._
import com.ruten.db._
import com.ruten.message._

object Routing extends DBAcess with Tools {

  // 取得 routing handler
  def apply() : Route = {
    return berkerlyRoutes
  }

  val berkerlyRoutes : Route = {

    get{

      path("berkerlydb"/"retrieve"/"gno"/"recommend") { 

        parameters('gno, 'gclass) {(gno, gclass) =>

          val recommend_gno: Future[Option[String]] = retrieve(gno)    

          onSuccess(recommend_gno) {

            case Some(data) => {  

              val msg: Message = Message(gno,data)
              val respMsg: JsValue = msg.toJson
              complete(200, s"$respMsg")

            }

            case None =>  {

              val recommend_hot_gno: Future[Option[String]] = retrieve_hot(gclass)

              onSuccess(recommend_hot_gno) {

                case Some(data) => {

                  val msg: Message = Message(gno,data)
                  val respMsg: JsValue = msg.toJson
                  complete(200, s"$respMsg")

                }

                case None => complete(HttpResponse(StatusCodes.InternalServerError , entity = s"the data is not fetched and something went wrong"))

              }
            }
          }
        }
      }
    }
  }
}




