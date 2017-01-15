package me.niccorder.shop.api

import com.google.inject.{Inject, Module}
import com.twitter.finagle.Thrift
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.{CommonFilters, ExceptionMappingFilter, LoggingMDCFilter, TraceIdMDCFilter}
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.json.modules.FinatraJacksonModule
import com.twitter.finatra.thrift._
import com.twitter.finatra.thrift.routing.ThriftRouter
import me.niccorder.shop.datastore.ItemDatastoreController

object ItemApiServerMain extends ItemApiServer

class ItemApiServer extends HttpServer with ThriftServer {

  override val name: String = "s/item-datastore-server"

  override def thriftPort: Option[Int] = Option.apply(8031)

  override protected def modules: Seq[Module] =
    super.modules ++
      Seq(
        ItemModule,
        FinatraJacksonModule
      )

  override protected def configureHttp(router: HttpRouter): Unit =
    router
      .filter[TraceIdMDCFilter[Request, Response]]
      .filter[CommonFilters]
      .add[ItemController]

  override protected def configureThrift(router: ThriftRouter): Unit =
    router
      .add[ItemDatastoreController]
}
