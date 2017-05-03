package com.company.util

import com.company.message._

import akka.http.scaladsl.server._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model._
// json render
import spray.json._

object ErrorHandler {

  // rejection
  implicit def rejectionHandler = RejectionHandler.newBuilder()
    .handle {
      case MissingQueryParamRejection(parameterName) => {
        val message: String = s"Missing parameter '${parameterName}'"
        val errorMsg: ErrorMessage = ErrorMessage(message)
        val respMsg: JsValue = errorMsg.toJson
        complete(400, s"${respMsg}")
      } 
      case MethodRejection(method) => {
        val message: String = s"Require method type '${method.name}'"
        val errorMsg: ErrorMessage = ErrorMessage(message)
        val respMsg: JsValue = errorMsg.toJson
        complete(405, s"${respMsg}")
      }
    }
    .handleNotFound {
      val message: String = "Request uri can not be found."
      val errorMsg: ErrorMessage = ErrorMessage(message)
      val respMsg: JsValue = errorMsg.toJson
      complete(404, s"${respMsg}")
    }
    .result()

  // exception
  implicit def exceptionHandler: ExceptionHandler = ExceptionHandler {
    case ex: Exception =>
      extractRequest { req =>
        val method: HttpMethod= req._1
        val uri: Uri = req._2
        val header: Seq[HttpHeader] = req._3
        val reqEntity: RequestEntity = req._4
        val protocol: HttpProtocol = req._5
        
        val allHeader = header.mkString(",")
        val message :String = s"Request to '$uri' with header '${allHeader}' could not be handled normally, because of reason: '$ex'"
        val errorMsg: ErrorMessage = ErrorMessage(message)
        val respMsg: JsValue = errorMsg.toJson
        complete(408, s"${respMsg}")
      }
  }

}
