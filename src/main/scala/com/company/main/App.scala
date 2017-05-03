package com.company.main

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import com.company.util._
import scala.util.control.Breaks._

// shutdownhook
import akka.http.scaladsl.Http.ServerBinding
import scala.concurrent.Future

object App extends Tools{

  private val (host, port) = (GetConfig("ip.ip"), GetConfig("ip.port").toInt)

  def main(args: Array[String]){
	implicit val system = ActorSystem("akka-alsoview")
	implicit val materializer = ActorMaterializer()

	// request handler
	val bindingFuture = Http().bindAndHandle(Routing(), host, port)

    sys.addShutdownHook(new Thread {
      override
      def run() {
        print("close")
        shutdownHook(system, bindingFuture)
      }
    })
	println(s"Server online at http://$host:$port/\nPress 'Ctrl+C' to stop...")
    while(true){}
  }

  def shutdownHook(system: ActorSystem, bindingFuture: Future[ServerBinding]) {
    // needed for the future flatMap/onComplete in the end
	implicit val executionContext = system.dispatcher
    
    // bind complete
	bindingFuture
		.flatMap(_.unbind()) // trigger unbinding from the port	
		.onComplete(_ => system.terminate()) // and shutdown when done

	// bind failed
	bindingFuture
		.onFailure {
		  case ex: Exception =>
			println(s"Failed to bind to $host:$port")
			//log.error(ex, "Failed to bind to {}:{}!", host, port)
		}
  }
}
