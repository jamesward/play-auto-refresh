package com.jamesward.play

import sbt._
import Keys._
import unfiltered.netty.websockets._
import unfiltered.netty.websockets.Open

object BrowserNotifierPlugin extends Plugin {

  val openSockets = collection.mutable.ListBuffer.empty[WebSocket]
  WebSocketServer("/", 9001) {
    case Open(s) => openSockets += s
    case Close(s) => openSockets -= s
    case Error(s, e) => println(e.getMessage)
  }.start()

  val browserNotification = TaskKey[Unit]("browser-notification")
  
  val compileTask = (compile in Compile, baseDirectory, state) mapR { (a, dir, state) =>
    openSockets foreach (_.send("reload"))
  }
  
  val playAssetsDirectories = SettingKey[Seq[File]]("play-assets-directories")

  override lazy val projectSettings = super.projectSettings ++ Seq(
    watchSources <++= playAssetsDirectories map { dirs =>
      for {
        dir <- dirs
        file <- (dir.*** --- dir).get
      } yield file
    },
    watchSources <++= baseDirectory map { path => ((path / "app/assets") ** "*").get },
    BrowserNotifierPlugin.browserNotification <<= BrowserNotifierPlugin.compileTask.triggeredBy(compile in Compile)
  )
  
}
