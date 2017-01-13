package me.niccorder.shop.datastore

import com.twitter.finatra.thrift.ThriftServer
import com.twitter.finatra.thrift.filters._
import com.twitter.finatra.thrift.routing.ThriftRouter
import com.twitter.inject.annotations.Lifecycle

object ItemDatastoreServerMain extends ItemDatastoreServer

class ItemDatastoreServer extends ThriftServer {

  override val name = "item-datastore-server"

  override def configureThrift(router: ThriftRouter) {
    router
      .filter[LoggingMDCFilter]
      .filter[TraceIdMDCFilter]
      .filter[ThriftMDCFilter]
      .filter[AccessLoggingFilter]
      .filter[StatsFilter]
      .filter[ExceptionTranslationFilter]
      .add[ItemDatastoreController]
  }

  @Lifecycle protected override
  def postWarmup(): Unit = super.postWarmup()
}
