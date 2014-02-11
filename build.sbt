name := "Rontech"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache
)     

libraryDependencies += "org.mongodb" %% "casbah" % "2.6.3"

play.Project.playScalaSettings
