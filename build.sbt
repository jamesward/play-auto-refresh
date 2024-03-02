sbtPlugin := true

name := "play-auto-refresh"

organization := "com.jamesward"

scalaVersion := "2.12.18"

scalacOptions ++= Seq("-unchecked", "-deprecation")

javacOptions ++= Seq("--release", "11")

description := "Auto refresh for Play Framework apps"

homepage := Some(url("https://github.com/jamesward/play-auto-refresh"))

libraryDependencies += "ws.unfiltered" %% "unfiltered-netty-websockets" % "0.12.0"

licenses += "MIT" -> url("http://opensource.org/licenses/MIT")

addSbtPlugin("org.playframework" % "sbt-plugin" % "3.0.2" % Provided)

// Customise sbt-dynver's behaviour to make it work with tags which are/aren't v-prefixed
ThisBuild / dynverVTagPrefix := true

// Sanity-check: assert that version comes from a tag (e.g. not a too-shallow clone)
// https://github.com/dwijnand/sbt-dynver/#sanity-checking-the-version
Global / onLoad := (Global / onLoad).value.andThen { s =>
  dynverAssertTagVersion.value
  s
}

developers ++= List(Developer(
    "jamesward",
    "James Ward",
    "james@jamesward.com",
    url("https://github.com/jamesward")
  ),
)
