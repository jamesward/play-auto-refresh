sbtPlugin := true

name := "play-auto-refresh"

organization := "com.jamesward"

scalaVersion := "2.12.18"

scalacOptions ++= Seq("-unchecked", "-deprecation")

javacOptions ++= Seq("--release", "11")

description := "Auto refresh for Play Framework apps"

publishMavenStyle := false

libraryDependencies += "ws.unfiltered" %% "unfiltered-netty-websockets" % "0.12.0"

licenses += "MIT" -> url("http://opensource.org/licenses/MIT")

enablePlugins(GitVersioning)

git.useGitDescribe := true

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.9.0" % Provided)
