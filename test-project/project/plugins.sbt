addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.9.0")

lazy val playAutoRefreshPlugin = RootProject(file("..").getAbsoluteFile.toURI)

lazy val root = Project("test-project", file(".")).dependsOn(playAutoRefreshPlugin)
