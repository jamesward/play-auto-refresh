enablePlugins(PlayScala)

name := "test-project"

crossScalaVersions := Seq("2.13.15", "3.3.4")

scalaVersion := crossScalaVersions.value.head

libraryDependencies += guice

shouldOpenBrowser := true
