package me.niccorder.shop.datastore

import com.twitter.finatra.thrift.ThriftServer
import com.twitter.finatra.thrift.filters._
import com.twitter.finatra.thrift.routing.ThriftRouter

object ItemDatastoreServerMain extends ItemDatastoreServer

class ItemDatastoreServer extends ThriftServer {

  override val name: String = "Item Datastore"

  override protected def configureThrift(router: ThriftRouter): Unit = {
    router
      .filter[LoggingMDCFilter]
      .filter[TraceIdMDCFilter]
      .filter[ThriftMDCFilter]
      .filter[AccessLoggingFilter]
      .filter[StatsFilter]
      .filter[ClientIdWhitelistFilter]
  }

}
