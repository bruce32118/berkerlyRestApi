package com.ruten.service
import akka.actor.ActorSystem
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.ruten.util._
import com.ruten.db._
import com.github.blemale.scaffeine.{ Cache, Scaffeine }
import akka.actor.{Actor, ActorLogging}
import scala.concurrent.ExecutionContext.Implicits.global
import akka.event.Logging
import akka.event.LoggingAdapter
import scala.concurrent.Future


object Routing extends DBAcess with Tools{
  
  private var cache: Cache[String, String] = null
  
  private var logger: LoggingAdapter = null

  //private var logger: LogSource[ActorSystem, Class] = null
  // 取得 routing handler
  def apply(cache: Cache[String, String],system :ActorSystem) : Route = {
    logger = Logging.getLogger(system,this)
    this.cache = cache
    berkerlyRoutes
  }


 val berkerlyRoutes : Route = {

    get{
    
    path("berkerlydb"/"retrieve"/"gno"/"recommend"){ 

    parameters('gno, 'gclass) {(gno, gclass) =>

    
        
         try{    
            
            cache.getIfPresent(gno + gclass) match {

              case Some(data) =>  complete(HttpResponse(StatusCodes.OK, entity = data))

              case None =>  {
              
                   val recommend_gno: Future[Option[String]] = retrieve(gno)    

                   onSuccess(recommend_gno){

                      case Some(data) => {  

                           val jsondata = turnjson(data, 1)
                           cache.put(gno,jsondata)

                            complete(HttpResponse(StatusCodes.OK, entity = jsondata))

                      }

                      case None =>  {

                           val recommend_hot_gno: Future[Option[String]] = retrieve_hot(gclass)

                           onSuccess(recommend_hot_gno){

                             case Some(data) => {

                                  val jsondata_hot = turnjson(data, 2)

                                  cache.put(gno,jsondata_hot)

                                 complete(HttpResponse(StatusCodes.OK, entity = jsondata_hot))
                                
                                }

                              case None => complete(HttpResponse(StatusCodes.InternalServerError , entity = s"the data is not fetched and something went wrong"))
                              
                            }
                           
                           

                      }

                      
                    }

                  }

            }

          }catch{

              case ex: Throwable =>
              logger.error(ex, ex.getMessage)
              complete(HttpResponse(StatusCodes.InternalServerError , entity = s"Error found for gno $gno"))
              
          }
       
      }  
     }
   }
 }




}




