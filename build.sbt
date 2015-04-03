name := "scalaCard"

scalaVersion := "2.11.5"

version := "0.0.1-SNAPSHOT"

libraryDependencies ++= Seq (
  "org.scalacheck" %% "scalacheck" % "1.11.6"
  )

scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked"
)
