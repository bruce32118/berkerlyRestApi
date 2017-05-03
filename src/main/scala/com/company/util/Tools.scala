package com.company.util
import java.util.Date
import java.util.Calendar  
import net.liftweb.json.JsonAST
import net.liftweb.json.Printer._
import net.liftweb.json.JsonDSL._
import java.net._
import org.joda.time.Duration
import org.joda.time.format._
import org.joda.time._

trait Tools{

         def now = {    
                
            val date = Calendar.getInstance

            import date._

            getTime   
         
         }

         def turnjson(inputdata : String , mode : Int) : String = {
            
           mode match {

               case 1 => {
                   
                   val json1 = ("time" -> now.toString) ~ ("gno" -> inputdata.split(",")(0)) ~ ("recommend_gno_list" -> inputdata.split(",")(1))
                            
                   compact(JsonAST.render(json1))

               } 

               case 2 => {

                   val json1 = ("time" -> now.toString) ~ ("recommend_gno_list" -> inputdata)
                            
                   compact(JsonAST.render(json1))


               } 
           } 
            

         } 

               
          
         def getLocalIP = {

        	val localhost = InetAddress.getLocalHost  

        	localhost.getHostAddress

         }


}
