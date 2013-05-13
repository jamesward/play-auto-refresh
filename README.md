Play Auto Refresh
=================

This is an SBT plugin for Play Framework apps which works with a Chrome Extension to auto-refresh your browser when you make changes to your app.

Setup
-----

1. Add the SBT plugin to your `project/plugins.sbt` file (make sure to add an empty line before this one):

        addSbtPlugin("com.jamesward" %% "play-auto-refresh" % "0.0.1")

2. Add the [Play Framework Tools](https://chrome.google.com/webstore/detail/play-framework-tools/dchhggpgbommpcjpogaploblnpldbmen) Chrome Extension

3. Start your Play app in file watch mode:

        play ~run

4. Open your application in your browser: [http://localhost:9000](http://localhost:9000)

5. Make a change to the code for your application and watch your changes magically appear in your browser!



Build
-----


Release
-------

1. Set the release version in `build.sbt`
2. Commit
3. Tag git `v0.1.2`
4. Release: `sbt publish-signed`
