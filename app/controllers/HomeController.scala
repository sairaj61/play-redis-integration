package controllers

import akka.util.Timeout
import javax.inject._
import play.api._
import play.api.mvc._
import play.api.cache.redis.{AsynchronousResult, CacheAsyncApi}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps
/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents, cache: CacheAsyncApi) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */

  cache.set("key", "value")
  implicit val timeout: Timeout = Timeout(10 seconds)
  val entry: AsynchronousResult[Option[String]] =cache.get("key")
  val Actorresponse = Await.result(entry, timeout.duration)
  Actorresponse match {
    case None=>println("value not found")
    case Some(value)=>println("value="+value)
  }
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def explore() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.explore())
  }

  def tutorial() = Action { implicit request: Request[AnyContent] =>
    Ok("i m working fine")
//      .withHeaders(
//        "Access-Control-Allow-Origin" -> "*"
//      )
  }

}
