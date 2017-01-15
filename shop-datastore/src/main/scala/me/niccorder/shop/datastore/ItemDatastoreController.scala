package me.niccorder.shop.datastore

import com.twitter.finatra.thrift.Controller
import com.twitter.util.Future
import me.niccorder.shop.{Item, ItemDatastore}
import me.niccorder.shop.ItemDatastore.{CreateItem, DeleteItem, GetItem}
import javax.inject.Singleton

@Singleton
class ItemDatastoreController
    extends Controller
    with ItemDatastore.BaseServiceIface {

  val memoryStore: Map[Int, Item] = Map.empty

  override val createItem = handle(CreateItem) { args =>
    Future.apply {
      memoryStore + (args.item.itemId.toInt -> args.item)
      args.item.itemId
    }
  }

  override val deleteItem = handle(DeleteItem) { args =>
    Future.apply {
      memoryStore - args.id
      args.id.toString
    }
  }

  override val getItem = handle(GetItem) { args =>
    Future.apply {
      memoryStore.apply(args.id)
    }
  }
}
