package com.jamesward.play

import sbt._
import Keys._
import unfiltered.netty.websockets._
import unfiltered.netty.websockets.Open

object BrowserNotifier extends Plugin {

  val sockets = collection.mutable.ListBuffer.empty[WebSocket]
  WebSocketServer("/", 9001) {
    case Open(s) => sockets += s
    case Close(s) => sockets -= s
    case Error(s, e) => println(e.getMessage)
  }.start()

  val browserNotification = TaskKey[Unit]("browser-notification")
  val browserNotificationTask = (compile in Compile, baseDirectory, state) mapR { (a, dir, state) => 
    sockets foreach (_.send("reload"))
  }

  override lazy val projectSettings = super.projectSettings ++ Seq(
    BrowserNotifier.browserNotification <<= BrowserNotifier.browserNotificationTask.triggeredBy(compile in Compile)
  )
  
}
