import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "br.unb.cic.tp1",
      scalaVersion := "2.12.3",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Hello",
    libraryDependencies += scalaTest % Test
  )
