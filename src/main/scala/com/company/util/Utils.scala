package com.company.util
import com.typesafe.config.ConfigFactory
import java.net.InetAddress

object GetConfig {

  def apply(path: String) :String = {
    var info = ConfigFactory.load().getString(path)
    if(path == "ip.ip" && info == null)
      info = InetAddress.getLocalHost.getHostAddress
    return info
  }

}
