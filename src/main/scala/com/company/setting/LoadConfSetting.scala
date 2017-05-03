package com.company.setting

import org.apache.log4j.Logger
import org.apache.log4j.Level
import com.typesafe.config.ConfigFactory
import java.net._

class LoadConfSetting(val config_name : String){

  val config =  ConfigFactory.load().getConfig(config_name)
   
  def getConfigData(tag_name : String) : String = config.getString(tag_name)

  def SetLogger = {

	Logger.getLogger("org").setLevel(Level.OFF)
	Logger.getLogger("com").setLevel(Level.OFF)
	System.setProperty("spark.ui.showConsoleProgress","false")
	Logger.getRootLogger().setLevel(Level.OFF)

  }

}


class getLocalIP{

  val localhost = InetAddress.getLocalHost  

  def apply() : String = localhost.getHostAddress

}


class dbSetting{

  val dbacess = new LoadConfSetting("db")

  def apply(data : String) : String = dbacess.getConfigData(data)

}
