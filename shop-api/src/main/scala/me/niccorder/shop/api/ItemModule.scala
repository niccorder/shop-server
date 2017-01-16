package me.niccorder.shop.api

import java.net.InetSocketAddress

import com.google.inject.{Provides, Singleton}
import com.twitter.finagle.{Thrift, ThriftMux}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.thrift.ClientId
import com.twitter.finagle.tracing.DefaultTracer
import com.twitter.finatra.http.filters.{LoggingMDCFilter, TraceIdMDCFilter}
import com.twitter.inject.TwitterModule
import me.niccorder.shop.ItemDatastore
import me.niccorder.shop.api.model.InMemoryItemDatastore

@Singleton
object ItemModule extends TwitterModule {

  private implicit val itemDatastoreDestFlag = flag(
    "item.datastore.dest",
    "localhost:8031",
    "The thrift port used for the item datastore."
  )

  private implicit val itemDatastoreStatsFlag = flag(
    "item.datastore.dest",
    "s/item-datastore-server",
    "The item datastore's destination path."
  )

  @Provides
  @Singleton
  def provideLoggingMDCFilter(): LoggingMDCFilter[Request, Response] =
    new LoggingMDCFilter

  @Provides
  @Singleton
  def provideTraceMDCFilter(): TraceIdMDCFilter[Request, Response] =
    new TraceIdMDCFilter

  @Provides
  @Singleton
  def provideItemDatastoreClient(): InMemoryItemDatastore = {
    debug("providing ItemDatastore.FutureIface")

    new InMemoryItemDatastore
  }
}
