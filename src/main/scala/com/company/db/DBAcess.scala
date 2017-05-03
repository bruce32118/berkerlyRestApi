package com.company.db

import com.ruten.tools.berkeley.BerkeleyDbLocalFactory._
import com.ruten.tools.berkeley.BerkeleyDbLocalFactory 
import scala.util.Try
import java.io._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import com.ruten.util._

trait DBAcess{

   val writer = new BerkeleyDbLocalFactory

   def retrieve[T](gno: T): Future[Option[String]] = Future{
    
    val path = GetConfig("db.path")
    val name = GetConfig("db.name")
    require(path != null, "dbpath is null.") 
    require(name != null, "dbname is null.") 

    val groupId = (gno.toString.hashCode)%10 + 10
    
   	Try(writer.get(new File(path + groupId), name, groupId.toString, gno.toString))
      .toOption
      .flatMap{Option(_)}

   }

   def retrieve_hot[T](gclass: T): Future[Option[String]] = Future{
  
    val hot_path = GetConfig("db.hot_path")
    val hot_name = GetConfig("db.hot_name")

    require(hot_path != null, "db_hot_path is null.") 
    require(hot_name != null, "db_hot_name is null.") 
    
   	Try(writer.get(new File(hot_path + gclass),hot_name, gclass.toString, gclass.toString))
      .toOption
      .flatMap{Option(_)}

   }


}	


   
