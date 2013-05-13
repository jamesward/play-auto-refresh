name := "play-auto-refresh"

organization := "com.jamesward"

scalaVersion := "2.9.2"

version := "0.0.1"

sbtPlugin := true

libraryDependencies ++= Seq("net.databinder" %% "unfiltered-netty-websockets" % "0.6.8")

publishTo <<= version { v: String =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishMavenStyle := true

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

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")