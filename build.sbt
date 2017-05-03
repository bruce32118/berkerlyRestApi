name := "berkerlyRestApi"

version := "1.0"

scalaVersion := "2.11.8"

lazy val akkaHttpVersion = "2.4.11"

//libraryDependencies += "com.typesafe.akka" %% "akka-http-experimental" % akkaHttpVersion
libraryDependencies += "com.typesafe.akka" % "akka-http_2.11" % "10.0.4"
libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.4"

resolvers += Resolver.bintrayRepo("tabdulradi", "maven")

libraryDependencies += "net.liftweb" % "lift-json_2.11" % "2.6.2"

libraryDependencies += "com.typesafe" % "config" % "1.3.1"

//libraryDependencies += "io.spray" % "spray-caching" % "1.3.1"
libraryDependencies += "joda-time" % "joda-time" % "2.9.7"

libraryDependencies += "com.github.blemale" % "scaffeine_2.11" % "2.0.0"

//libraryDependencies += "io.kamon" %% "kamon-core" % "0.6.0"
  // [Optional]
//libraryDependencies +=  "io.kamon" %% "kamon-statsd" % "0.6.0"
  // [Optional]
//libraryDependencies +=  "io.kamon" %% "kamon-datadog" % "0.6.0"


fork := true

assemblyMergeStrategy in assembly := {
    case PathList(ps @ _*) if ps.last endsWith ".class"=> MergeStrategy.first
    case PathList(ps @ _*) if ps.last endsWith ".properties"=> MergeStrategy.first
    case PathList(ps @ _*) if ps.last endsWith ".xml"=> MergeStrategy.first
    case PathList(ps @ _*) if ps.last endsWith ".thrift"=> MergeStrategy.first
    case PathList(ps @ _*) if ps.last endsWith ".html"=> MergeStrategy.first
    case PathList(ps @ _*) if ps.last endsWith ".js"=> MergeStrategy.first
    case PathList(ps @ _*) if ps.last endsWith ".css"=> MergeStrategy.first
    case PathList(ps @ _*) if ps.last endsWith ".xsd"=> MergeStrategy.first
    case PathList(ps @ _*) if ps.last endsWith ".dtd"=> MergeStrategy.first
    case PathList(ps @ _*) if ps.last endsWith ".java"=> MergeStrategy.first
    case PathList(ps @ _*) if ps.last endsWith ".so"=> MergeStrategy.first
    case PathList(ps @ _*) if ps.last endsWith ".png"=> MergeStrategy.first
    case x =>
       val oldStrategy = (assemblyMergeStrategy in assembly).value
       oldStrategy(x)
}


