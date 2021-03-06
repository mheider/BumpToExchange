
scalaVersion := "2.11.6"

version := "0.1.1"

name := "BumpToExchange"
organization := "de.hdrmrks.bte"

javacOptions in Compile ++= Seq("-source", "1.7", "-target", "1.7")

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-encoding", "utf8")

libraryDependencies ++= Seq("com.github.max-leuthaeuser" % "scroll_2.11" % "0.9",
  "org.scala-lang.modules" % "scala-swing_2.11" % "2.0.0-M2",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test")
