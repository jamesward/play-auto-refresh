enablePlugins(PlayScala)

name := "test-project"

crossScalaVersions := Seq("2.13.14", "3.3.3")

scalaVersion := crossScalaVersions.value.head

libraryDependencies += guice

shouldOpenBrowser := true
