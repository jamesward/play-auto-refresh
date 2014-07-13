package com.jamesward.play

import com.typesafe.sbt.web.SbtWeb
import sbt.Keys._
import sbt._
import unfiltered.netty._
import unfiltered.netty.websockets._
import unfiltered.request.{GET, Path}

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

  override def requires: Plugins = SbtWeb

  override def trigger: PluginTrigger = AllRequirements

  val browserNotification = TaskKey[Unit]("browser-notification")

  val port = sys.props.get("http.port").getOrElse("9000")

  val compileTask = (compile in Compile, baseDirectory, state) mapR { (a, dir, state) =>
    openSockets foreach (_.send(s"reload:$port"))
  }

  override def projectSettings: Seq[Def.Setting[_]] = Seq(
    // conf directory
    watchSources <++= unmanagedResourceDirectories in Compile map { dirs =>
      for {
        dir <- dirs
        file <- (dir.*** --- dir).get
      } yield file
    },
    watchSources <++= baseDirectory map { path => ((path / "app/assets") ** "*").get},
    browserNotification <<= compileTask.triggeredBy(compile in Compile)
  )
}
