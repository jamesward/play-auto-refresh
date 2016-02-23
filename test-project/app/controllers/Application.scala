package controllers

import play.api._
import play.api.mvc._

class Application extends Controller {

  def index = Action {
    Ok("hello, world")
  }

}
