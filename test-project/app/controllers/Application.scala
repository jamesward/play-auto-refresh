package controllers

import play.api._
import play.api.mvc._

class Application extends InjectedController {

  def index = Action {
    Ok(views.html.index())
  }

}
