Play Auto Refresh
=================

This is an SBT plugin for Play Framework apps which works with a Chrome Extension to auto-refresh your browser when you make changes to your app.

Setup
-----

1. Add the SBT plugin to your `project/plugins.sbt` file (make sure to add an empty line before this one):

        addSbtPlugin("com.jamesward" %% "play-auto-refresh" % "0.0.3")

2. Add the [Play Framework Tools](https://chrome.google.com/webstore/detail/play-framework-tools/dchhggpgbommpcjpogaploblnpldbmen) Chrome Extension

3. Start your Play app in file watch mode:

        play ~run

4. Open your application in your browser: [http://localhost:9000](http://localhost:9000)

5. Make a change to the code for your application and watch your changes magically appear in your browser!


Release Info
------------

* 0.0.1 - Push changes on compile with default watches
* 0.0.2 - Add `/public` and `/app/assets` to default watches
* 0.0.3 - Fix botched release


Developer Info
--------------

### Setup a Test App

In a Play app add a `project/project/Build.scala` file containing:

```
import sbt._

object PluginDef extends Build {
  override lazy val projects = Seq(root)
  lazy val root = Project("plugins", file(".")).dependsOn(playAutoRefreshPlugin)
  lazy val playAutoRefreshPlugin = file("../../play-auto-refresh")
}
```

Run the Play app with `~run` and then test that reloading works.  To recompile the `play-auto-refresh` plugin, restart the Play app.

### Release

1. Set the release version in `build.sbt`
2. Git Commit
3. Git Tag
4. Release to the OSS repo: `sbt publish-signed`

