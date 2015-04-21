import bintray.Keys._

name := "play-auto-refresh"

organization := "com.jamesward"

scalaVersion := "2.10.3"

javacOptions ++= Seq("-source", "1.6", "-target", "1.6")

version := "0.0.12"

sbtPlugin := true

libraryDependencies += "net.databinder" %% "unfiltered-netty-websockets" % "0.8.0"

publishMavenStyle := false

bintrayPublishSettings

repository in bintray := "sbt-plugins"

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

bintrayOrganization in bintray := None

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.3.+" % "provided")
