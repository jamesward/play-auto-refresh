name := "play-auto-refresh"

organization := "com.jamesward"

scalaVersion := "2.10.6"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

description := "auto refresh for play with scala and scala.js cross project"

sbtPlugin := true

publishMavenStyle := false

libraryDependencies += "net.databinder" %% "unfiltered-netty-websockets" % "0.8.0"

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

git.useGitDescribe := true

enablePlugins(GitVersioning)

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.4.3" % "provided")
