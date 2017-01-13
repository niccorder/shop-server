package me.niccorder.shop.api

import java.net.InetSocketAddress

import com.google.inject.{Provides, Singleton}
import com.twitter.finagle.Thrift
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.filters.{LoggingMDCFilter, TraceIdMDCFilter}
import com.twitter.inject.TwitterModule
import me.niccorder.shop.ItemDatastore

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
  def provideItemDatastoreClient(): ItemDatastoreClient = {
    debug("providing ItemDatastore.FutureIface")

    ItemDatastoreClient(
      Thrift.client.newServiceIface[ItemDatastore.FutureIface](
        itemDatastoreDestFlag.apply,
        itemDatastoreStatsFlag.apply
      )
    )
  }
}
