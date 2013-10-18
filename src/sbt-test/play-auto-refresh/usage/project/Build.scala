import sbt._
import Keys._
import com.jamesward.play.BrowserNotifierPlugin._

object ApplicationBuild extends Build {
  val main = play.Project("a", "1", Seq(livereload))
}