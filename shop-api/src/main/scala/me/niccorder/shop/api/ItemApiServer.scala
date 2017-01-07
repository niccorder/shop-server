package me.niccorder.shop.api

import com.google.inject.{Inject, Module}
import com.twitter.finatra.thrift.ThriftServer
import com.twitter.finatra.thrift.routing.ThriftRouter

object ItemApiServerMain extends ItemApiServer

class ItemApiServer extends ThriftServer {

  override val name: String = "item-datastore"

  override protected def configureThrift(router: ThriftRouter): Unit =
    router
      .add[ItemController]

}
