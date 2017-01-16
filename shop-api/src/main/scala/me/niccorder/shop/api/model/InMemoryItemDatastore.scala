package me.niccorder.shop.api.model

import com.twitter.util.Future
import me.niccorder.shop.Item

class InMemoryItemDatastore {
  var inMemoryStore: Map[Int, Item] = Map()

  def createItem(item: Item): Future[String] = {
    Future {
      inMemoryStore += (item.itemId.toInt -> item)

      item.itemId
    }
  }

  def deleteItem(id: Int): Future[String] = {
    Future {
      inMemoryStore -= id

      id.toString
    }
  }

  def getItem(id: Int): Future[Item] = {
    Future {
      inMemoryStore.apply(id)
    }
  }

  def getItems(): Future[List[Item]] = {
    Future {
      inMemoryStore.values.toList
    }
  }
}
