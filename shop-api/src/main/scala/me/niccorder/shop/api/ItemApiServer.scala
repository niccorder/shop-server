package me.niccorder.shop.api

import com.google.inject.Module
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter

object ItemApiServerMain extends ItemApiServer

class ItemApiServer extends HttpServer {

  override val name: String = "item-datastore"


  override protected def modules: Seq[Module] = Seq(
    ItemModule
  )

  override protected def configureHttp(router: HttpRouter): Unit =
    router
      .add[ItemController]
}
