addSbtPlugin("org.playframework" % "sbt-plugin" % "3.0.8")

lazy val playAutoRefreshPlugin = RootProject(file("..").getAbsoluteFile.toURI)

lazy val root = Project("test-project", file(".")).dependsOn(playAutoRefreshPlugin)
