package com.one2nc.scalafinatrainstrumentation
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter

class APIServer extends HttpServer {
   override def configureHttp(router: HttpRouter): Unit = {
       router.add[APIController]
  }
}
