package com.jamesward.play

import java.awt.Desktop

import play.sbt.PlayImport.PlayKeys
import play.sbt.{Play, PlayRunHook}
import sbt.Keys._
import sbt._
import unfiltered.netty._
import unfiltered.netty.websockets._
import unfiltered.request.{GET, Path}

object BrowserNotifierKeys {
  val shouldOpenBrowser = SettingKey[Boolean]("should-open-browser", "Controls if the plugin should open the web " +
    "browser automatically when running the app")
}

object BrowserNotifierPlugin extends AutoPlugin {
  val openSockets = collection.mutable.ListBuffer.empty[WebSocket]

  try {
    Http(9001).handler(Planify({
      case GET(Path("/")) => {
        case Open(s) => openSockets += s
        case Close(s) => openSockets -= s
        case Error(s, e) => println(e.getMessage)
      }
    })).start()
    println("auto-refresh websocket started on port 9001")
  }
  catch {
    case _: Throwable => println("Could not start the auto-reload server. This is probably because it is already running, in which case everything should still work.")
  }

  override def requires: Plugins = Play

  override def trigger: PluginTrigger = AllRequirements

  val browserNotification = TaskKey[Unit]("browser-notification")

  val port = sys.props.get("http.port").getOrElse("9000")

  val compileTask = (compile in Compile, baseDirectory, state) mapR { (a, dir, state) =>
    openSockets foreach (_.send(s"reload:$port"))
  }

  private[this] def openBrowser(): Unit = {
    sys.props("os.name").toLowerCase match {
      case x if x contains "mac" => s"open http://localhost:$port".!
      case _ if Desktop.isDesktopSupported => Desktop.getDesktop.browse(new URI(s"http://localhost:$port"))
      case _ => println("Attempted to open web browser, but the current desktop environment is not supported.")
    }
  }

  val autoOpen = Def.setting {
    PlayRunHook.makeRunHookFromOnStarted { _ =>
      if (BrowserNotifierKeys.shouldOpenBrowser.value) openBrowser()
      else ()
    }
  }

  override def projectSettings: Seq[Def.Setting[_]] = Seq(
    // conf directory
    watchSources <++= unmanagedResourceDirectories in Compile map { dirs =>
      for {
        dir <- dirs
        file <- (dir.*** --- dir).get
      } yield file
    },
    // assets directory, might not be necessary with SbtWeb
    watchSources <++= baseDirectory map { path => ((path / "app/assets") ** "*").get},
    browserNotification <<= compileTask.triggeredBy(compile in Compile),
    BrowserNotifierKeys.shouldOpenBrowser := true,
    PlayKeys.playRunHooks += autoOpen.value
  )
}
