name := "play-auto-refresh"

organization := "com.jamesward"

scalaVersion := "2.9.2"

version := "0.0.1-SNAPSHOT"

sbtPlugin := true

libraryDependencies ++= Seq("net.databinder" %% "unfiltered-netty-websockets" % "0.6.8")
