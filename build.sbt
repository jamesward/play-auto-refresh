lazy val root = (project in file(".")).
  settings(
    name := "play-auto-refresh",
    version in ThisBuild := "0.0.14",
    scalaVersion := "2.10.6",
    organization in ThisBuild := "com.jamesward",
    sbtPlugin := true,
    description := "auto refresh for play with scala and scala.js cross project",
    licenses +=("MIT", url("http://opensource.org/licenses/MIT")),
    publishMavenStyle := false,
    javacOptions ++= Seq("-source", "1.8", "-target", "1.8")
  )

libraryDependencies += "net.databinder" %% "unfiltered-netty-websockets" % "0.8.0"
bintrayVcsUrl := Some("git@github.com/jamesward/play-auto-refresh.git")

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.4.3" % "provided")

