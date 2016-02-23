addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.4.6")

lazy val playAutoRefreshPlugin = file("..").getAbsoluteFile.toURI

lazy val root = Project("test-project", file(".")).dependsOn(playAutoRefreshPlugin)
