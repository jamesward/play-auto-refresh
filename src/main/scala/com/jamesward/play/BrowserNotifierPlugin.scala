package com.jamesward.play

import java.awt.Desktop

import play.sbt.PlayImport.PlayKeys
import play.sbt.{PlayRunHook, PlayWeb}
import sbt.Keys._
import sbt._

import scala.sys.process._
import unfiltered.netty._
import unfiltered.netty.websockets._
import unfiltered.request.{GET, Path}

object BrowserNotifierPlugin extends AutoPlugin {

  override def requires: Plugins = PlayWeb

  override def trigger: PluginTrigger = AllRequirements

  val webSockets = collection.mutable.Set.empty[WebSocket]

  object autoImport {
    val browserNotification = taskKey[Unit]("Browser Notification")
    val openBrowser = taskKey[Unit]("Open Play App in Browser")
    val shouldOpenBrowser = settingKey[Boolean]("Should the web browser automatically open when running the app")
  }

  import autoImport._

  def maybeOpenBrowser(shouldOpenBrowser: Boolean, playPort: Int, log: Logger): Unit = {
    if (shouldOpenBrowser) {
      sys.props("os.name").toLowerCase match {
        case x if x contains "mac" => s"open http://localhost:$playPort".!
        case _ if Desktop.isDesktopSupported => Desktop.getDesktop.browse(new URI(s"http://localhost:$playPort"))
        case _ => log.error("Attempted to open web browser, but the current desktop environment is not supported.")
      }
    }
  }

  def sendWebSocketMessage(playPort: Int): Unit = {
    webSockets.foreach(_.send(s"reload:$playPort"))
  }

  lazy val baseBrowserNotifierPluginSettings: Seq[Def.Setting[_]] = Seq(
    browserNotification := sendWebSocketMessage(PlayKeys.playDefaultPort.value),
    openBrowser := maybeOpenBrowser(shouldOpenBrowser.value, PlayKeys.playDefaultPort.value, streams.value.log),
    shouldOpenBrowser := true,
    PlayKeys.playRunHooks += new BrowserNotifierPlayRunHook(state.value, streams.value.log),
    Compile / compile := {
      val compileAnalysis = (Compile / compile).value
      browserNotification.value
      compileAnalysis
    }
  )

  override lazy val projectSettings: Seq[Def.Setting[_]] = super.projectSettings ++ baseBrowserNotifierPluginSettings

  class BrowserNotifierPlayRunHook(state: State, log: Logger) extends PlayRunHook {

    lazy val server: Server = {
      Server.local(9001).handler(
        Planify(
          {
            case GET(Path("/")) => {
              case Open(s) => webSockets += s
              case Close(s) => webSockets -= s
              case Error(_, e) => log.error(e.getMessage)
            }
          }
        )
      )
    }

    override def afterStarted(): Unit = {
      server.start()
      Project.runTask(openBrowser, state)
      log.info("Started auto-refresh WebSocket on port 9001")
    }

    override def afterStopped(): Unit = {
      server.stop()
      log.info("Stopped auto-refresh WebSocket on port 9001")
    }

  }

}
