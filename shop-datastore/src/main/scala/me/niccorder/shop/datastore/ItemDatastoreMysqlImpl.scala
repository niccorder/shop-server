package me.niccorder.shop.datastore

import com.twitter.util.Future
import me.niccorder.shop.{Item, ItemDatastore}


trait ItemDatastoreMysqlImpl extends ItemDatastore.FutureIface {

  override def createItem(item: Item): Future[String] = Future.apply("")

  override def deleteItem(id: Int): Future[String] = Future.apply("")

  override def getItem(id: Int): Future[Item] = Future.apply(Item.apply("12", "Cheese", 234.2, Option.apply("")))
}
