
scalaVersion := "2.11.6"

name := "BumpToExchange"

javacOptions in Compile ++= Seq("-source", "1.7", "-target", "1.7")

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-encoding", "utf8")

libraryDependencies ++= Seq("com.github.max-leuthaeuser" % "scroll_2.11" % "0.7")
