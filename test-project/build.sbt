enablePlugins(PlayScala)

name := "test-project"

crossScalaVersions := Seq("2.13.16", "3.3.5")

scalaVersion := crossScalaVersions.value.head

libraryDependencies += guice

shouldOpenBrowser := true
