addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.4")

lazy val playAutoRefreshPlugin = file("..").getAbsoluteFile.toURI

lazy val root = Project("test-project", file(".")).dependsOn(playAutoRefreshPlugin)
