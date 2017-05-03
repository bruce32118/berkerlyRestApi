package com.company.message

import java.text.SimpleDateFormat
import java.util.Calendar

// json render
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._
/**
 * @Author James Chien
 * @Version 1.0
 *
 * Message for RequestActor to build response.
 *
 * */
case class Message(gno: String, recommend: String, timestamp: String = DefaultTimestamp())
case class MessageResponse(message: Message)

case class ErrorMessage(err_message: String, timestamp: String = DefaultTimestamp())
case class ErrorMessageResponse(err_message: ErrorMessage)

case class SetMessageRequest(message: Message)
case class SetErrorMessageRequest(err_message: ErrorMessage)


object Message extends DefaultJsonProtocol {
  implicit def responseFormat = jsonFormat3(Message.apply)
}

object ErrorMessage extends DefaultJsonProtocol {
  implicit val responseFormat = jsonFormat2(ErrorMessage.apply)
}

object DefaultTimestamp {
  
  def apply() :String = {

     val timestamp_template = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
     val defaultTimeStamp: String = timestamp_template.format(Calendar.getInstance().getTime())
     return defaultTimeStamp
  
  } 
}

