enablePlugins(PlayScala)

name := "test-project"

crossScalaVersions := Seq("2.13.18", "3.3.7")

scalaVersion := crossScalaVersions.value.head

libraryDependencies += guice

shouldOpenBrowser := true
