Play Auto Refresh
=================

This is an SBT plugin for Play Framework apps which works with a Chrome Extension to auto-refresh your browser when you make changes to your app.

Setup
-----

1. Add the SBT plugin to your `project/plugins.sbt` file (make sure to add an empty line before this one):

        addSbtPlugin("com.jamesward" %% "play-auto-refresh" % "0.0.5")

    Note: For Play before 2.2.0 use version `0.0.4`.

2. Add ```import com.jamesward.play.BrowserNotifierPlugin._``` at the top of your build definition (ex: ```project/Build.scala```)

3. Add ```livereload``` to the play project settings ex:

```
object ApplicationBuild extends Build {
  val main = play.Project("a", "1", Seq( /* my settings */ ) ++ livereload )
}
```

4. Add the [Play Framework Tools](https://chrome.google.com/webstore/detail/play-framework-tools/dchhggpgbommpcjpogaploblnpldbmen) Chrome Extension

5. Start your Play app in file watch mode:

        play ~run

6. Open your application in your browser: [http://localhost:9000](http://localhost:9000)

7. Make a change to the code for your application and watch your changes magically appear in your browser!


Release Info
------------

* 0.0.1 - Push changes on compile with default watches
* 0.0.2 - Add `/public` and `/app/assets` to default watches
* 0.0.3 - Fix botched release
* 0.0.4 - Fix failure due to multiple instances trying to use the same port
* 0.0.5 - Bumps for Play 2.2.0 / sbt 0.13.0 and publish the sbt community repo
* 0.0.6 - Not overriding settings, so it could be used within a build with non play projects

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
2. Update the version in `README.md`
3. Git Commit
4. Git Tag
5. Release to the OSS repo: `sbt publish-signed`