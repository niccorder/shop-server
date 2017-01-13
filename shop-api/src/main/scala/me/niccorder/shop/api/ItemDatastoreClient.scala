package me.niccorder.shop.api

import me.niccorder.shop.ItemDatastore

/**
  * Due to limitations with injection, we create a wrapper for our thrift client.
  */
case class ItemDatastoreClient(
  client: ItemDatastore.FutureIface
)
