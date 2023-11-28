enablePlugins(PlayScala)

name := "test-project"

crossScalaVersions := Seq("2.13.12", "3.3.1")

scalaVersion := crossScalaVersions.value.head

libraryDependencies += guice

shouldOpenBrowser := true
