package me.niccorder.shop.datastore

import com.twitter.util.Future
import me.niccorder.shop.{Item, ItemDatastore}

class ItemDatastoreMysqlImpl extends ItemDatastore.FutureIface {

  class NotYetImplmentedException(msg: String) extends Exception(msg)

  override def createItem(item: Item): Future[String] = ???

  override def deleteItem(id: Int): Future[String] = ???

  override def getItem(id: Int): Future[Item] = Future.apply(Item.apply("12", "Cheese", 234.2, Option.apply("")))
}
