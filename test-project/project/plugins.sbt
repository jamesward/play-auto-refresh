addSbtPlugin("org.playframework" % "sbt-plugin" % "3.0.2")

lazy val playAutoRefreshPlugin = RootProject(file("..").getAbsoluteFile.toURI)

lazy val root = Project("test-project", file(".")).dependsOn(playAutoRefreshPlugin)
