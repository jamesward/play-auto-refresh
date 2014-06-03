import bintray.Keys._

name := "play-auto-refresh"

organization := "com.jamesward"

scalaVersion := "2.10.2"

version := "0.0.8"

sbtPlugin := true

libraryDependencies ++= Seq("net.databinder" %% "unfiltered-netty-websockets" % "0.7.0")

publishMavenStyle := false

bintrayPublishSettings

repository in bintray := "sbt-plugins"

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

bintrayOrganization in bintray := None
