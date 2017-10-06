lazy val root = (project in file(".")).enablePlugins(PlayScala)

name := "test-project"

scalaVersion := "2.12.3"

libraryDependencies += guice
