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
  val browserNotificationTask = (compile in Compile, baseDirectory, state) mapR { (a, dir, state) =>
    openSockets foreach (_.send("reload"))
  }

  override lazy val projectSettings = super.projectSettings ++ Seq(
    BrowserNotifierPlugin.browserNotification <<= BrowserNotifierPlugin.browserNotificationTask.triggeredBy(compile in Compile)
  )
  
}
