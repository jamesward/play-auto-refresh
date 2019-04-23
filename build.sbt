sbtPlugin := true

name := "play-auto-refresh"

organization := "com.jamesward"

scalaVersion := "2.12.3"

scalacOptions ++= Seq("-unchecked", "-deprecation")

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

description := "Auto refresh for Play Framework apps"

publishMavenStyle := false

libraryDependencies += "ws.unfiltered" %% "unfiltered-netty-websockets" % "0.9.1"

licenses += "MIT" -> url("http://opensource.org/licenses/MIT")

enablePlugins(GitVersioning)

git.useGitDescribe := true

bintrayVcsUrl := Some("git@github.com/jamesward/play-auto-refresh.git")

bintrayRepository := "sbt-plugins"

bintrayOrganization in bintray := None

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.7.0" % Provided)
