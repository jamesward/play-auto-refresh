import bintray.Keys._

name := "play-auto-refresh"

organization := "com.jamesward"

scalaVersion := "2.10.3"

javacOptions ++= Seq("-source", "1.6", "-target", "1.6")

version := "0.0.9"

sbtPlugin := true

libraryDependencies += "net.databinder" %% "unfiltered-netty-websockets" % "0.8.0"

publishMavenStyle := false

bintrayPublishSettings

repository in bintray := "sbt-plugins"

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

bintrayOrganization in bintray := None

addSbtPlugin("com.typesafe.sbt" % "sbt-web" % "1.0.2")
