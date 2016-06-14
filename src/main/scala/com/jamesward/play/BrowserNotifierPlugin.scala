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

  override def requires: Plugins = Play

  override def trigger: PluginTrigger = AllRequirements

  val browserNotification = TaskKey[Unit]("browser-notification")

  val openBrowser = TaskKey[Unit]("open-browser")

  val webSockets = collection.mutable.Set.empty[WebSocket]

  val port = sys.props.get("http.port").getOrElse("9000")

  val browserNotificationTask = Def.task[Unit] {
    webSockets.foreach(_.send(s"reload:$port"))
  }

  val openBrowserTask = Def.task[Unit] {
    if (BrowserNotifierKeys.shouldOpenBrowser.value) {
      sys.props("os.name").toLowerCase match {
        case x if x contains "mac" => s"open http://localhost:$port".!
        case _ if Desktop.isDesktopSupported => Desktop.getDesktop.browse(new URI(s"http://localhost:$port"))
        case _ => streams.value.log.error("Attempted to open web browser, but the current desktop environment is not supported.")
      }
    }
  }

  class BrowserNotifierPlayRunHook(state: State, streams: TaskStreams) extends PlayRunHook {

    import java.net.InetSocketAddress

    lazy val server = {
      Server.local(9001).handler(
        Planify(
          {
            case GET(Path("/")) => {
              case Open(s) => webSockets += s
              case Close(s) => webSockets -= s
              case Error(s, e) => streams.log.error(e.getMessage)
            }
          }
        )
      )
    }

    override def afterStarted(addr: InetSocketAddress): Unit = {
      server.start()
      Project.runTask(openBrowser, state)
      streams.log.info("Started auto-refresh WebSocket on port 9001")
    }

    override def afterStopped(): Unit = {
      server.stop()
      streams.log.info("Stopped auto-refresh WebSocket on port 9001")
    }

  }

  override def projectSettings: Seq[Def.Setting[_]] = Seq(
    browserNotification <<= browserNotificationTask.triggeredBy(compile in Compile),
    PlayKeys.playRunHooks += new BrowserNotifierPlayRunHook(state.value, streams.value),
    openBrowser <<= openBrowserTask,
    BrowserNotifierKeys.shouldOpenBrowser := true
  )

}
