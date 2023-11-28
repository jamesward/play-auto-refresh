Play Auto Refresh
=================

This is an SBT plugin for Play Framework (or sbt-web) apps which works with a Chrome Extension to auto-refresh your browser when you make changes to your app.

Setup
-----

1. Add the SBT plugin to your `project/plugins.sbt` file (make sure to add an empty line before this one):

```sbt
addSbtPlugin("com.jamesward" % "play-auto-refresh" % "0.0.20")
```

2. The plugin bootstraps itself automatically as soon as you enable Play in your project.

3. Add the [Play Framework Tools](https://chrome.google.com/webstore/detail/play-framework-tools/dchhggpgbommpcjpogaploblnpldbmen) Chrome Extension

4. Start your Play app in file watch mode:

```sh
sbt ~run
```

5. The browser window should open automatically. If you don't want this add the following to your `build.sbt`:

```sbt
shouldOpenBrowser := false
```

6. Make a change to the code for your application and watch your changes magically appear in your browser!


Release Info
------------

* 0.0.1 - Push changes on compile with default watches
* 0.0.2 - Add `/public` and `/app/assets` to default watches
* 0.0.3 - Fix botched release
* 0.0.4 - Fix failure due to multiple instances trying to use the same port
* 0.0.5 - Bumps for Play 2.2.0 / sbt 0.13.0 and publish the sbt community repo
* 0.0.6 - Avoid overriding settings: https://github.com/jamesward/play-auto-refresh/pull/8
* 0.0.7 - Fix incompatibility with Play 2.2.x
* 0.0.8 - Fix incompatibility with Play 2.3.x
* 0.0.9 - Migrate to an sbt 0.13.5 auto-plugin
* 0.0.10 - Use the configured Play port to tell the Chrome plugin which URL to reload
* 0.0.11 - Automatically open the browser window when you run your app
* 0.0.12 - Prevent plugin from failing when running in a headless environment
* 0.0.13 - Fix incompatibility with Play 2.4.x
* 0.0.14 - Bump to sbt 0.13.11
* 0.0.15 - Bump to Play 2.5
* 0.0.16 - Bump to Play 2.6 and sbt 1.0
* 0.0.17 - Bump to Play 2.7
* 0.0.18 - Bump to Play 2.8
* 0.0.19 - Bump to Play 2.9
* 0.0.20 - Bump to Play 3.0

Developer Info
--------------

### Chrome Extension

The source code of the extension can be found here: https://github.com/jamesward/play-framework-chrome-tools

### Test Project

1. `cd test-project`
1. `sbt ~run`
1. [Install the Play Framework Tools Chrome Extension](https://chrome.google.com/webstore/detail/play-framework-tools/dchhggpgbommpcjpogaploblnpldbmen)
1. Check out the app: [http://localhost:9000](http://localhost:9000)
1. Change a asset and watch your browser magically reload the changes!

### Release

1. Update the version and "Release Info" in `README.md`
1. Git Tag: `git tag -s v0.0.xx`
1. Push tag
1. Watch workflow doing the magic: https://github.com/jamesward/play-auto-refresh/actions/workflows/publish.yml
1. Wait (sometimes 15 minutes, sometimes 2 hours): https://repo1.maven.org/maven2/com/jamesward/play-auto-refresh_2.12_1.0
1. Enjoy!
