Play Auto Refresh
=================

This is an SBT plugin for Play Framework (or sbt-web) apps which works with a Chrome Extension to auto-refresh your browser when you make changes to your app.

Setup
-----

1. Add the SBT plugin to your `project/plugins.sbt` file (make sure to add an empty line before this one):

        addSbtPlugin("com.jamesward" % "play-auto-refresh" % "0.0.15")
        
2. The plugin bootstraps itself automatically as soon as you enable Play in your project.

3. Add the [Play Framework Tools](https://chrome.google.com/webstore/detail/play-framework-tools/dchhggpgbommpcjpogaploblnpldbmen) Chrome Extension

4. Start your Play app in file watch mode:

        sbt ~run
        
   or

        activator ~run

5. The browser window should open automatically (if you don't want this, set `BrowserNotifierKeys.shouldOpenBrowser := false`)

        For play 2.4.x, edit `build.sbt`:
        
        - Add `import com.jamesward.play.BrowserNotifierKeys` at header
        - Add `BrowserNotifierKeys.shouldOpenBrowser := false` at footer

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

Developer Info
--------------

### Test Project

1. `cd test-project`
1. `activator ~run`
1. [Install the Play Framework Tools Chrome Extension](https://chrome.google.com/webstore/detail/play-framework-tools/dchhggpgbommpcjpogaploblnpldbmen)
1. Check out the app: [http://localhost:9000](http://localhost:9000)
1. Change a asset and watch your browser magically reload the changes!

### Release

1. Update the version in `README.md`
1. Git Commit
1. Git Tag
1. Push tags
1. Publish: `sbt publish-signed`
