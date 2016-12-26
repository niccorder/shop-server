package me.niccorder.shop.api

import com.google.inject.{Provides, Singleton}
import com.twitter.finagle.Thrift
import com.twitter.inject.TwitterModule
import me.niccorder.shop.ItemDatastore

object ItemModule extends TwitterModule {

  @Provides
  @Singleton
  def provideService(): ItemDatastore.FutureIface =
    Thrift.client.newIface[ItemDatastore.FutureIface]("item-datastore-client")
}