name := "play-auto-refresh"

organization := "com.jamesward"

scalaVersion := "2.10.2"

version := "0.0.6"

sbtPlugin := true

libraryDependencies ++= Seq("net.databinder" %% "unfiltered-netty-websockets" % "0.6.8")

publishTo := Some(Resolver.url("sbt-plugin-releases", new URL("http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns))

publishMavenStyle := false

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>http://github.com/jamesward/play-auto-refresh</url>
  <licenses>
    <license>
      <name>MIT License"</name>
      <url>http://opensource.org/licenses/MIT</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:jamesward/play-auto-refresh.git</url>
    <connection>scm:git:git@github.com:jamesward/play-auto-refresh.git</connection>
    <developerConnection>scm:git:https://github.com/webjars/webjars-play.git</developerConnection>
  </scm>
  <developers>
    <developer>
      <id>jamesward</id>
      <name>James Ward</name>
      <url>http://www.jamesward.com</url>
      <email>james@jamesward.com</email>
    </developer>
  </developers>
  <parent>
    <groupId>org.sonatype.oss</groupId>
    <artifactId>oss-parent</artifactId>
    <version>7</version>
  </parent>
)
