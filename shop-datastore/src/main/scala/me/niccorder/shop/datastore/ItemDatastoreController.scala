package me.niccorder.shop.datastore

import com.twitter.finatra.thrift.Controller
import com.twitter.util.Future
import me.niccorder.shop.{Item, ItemDatastore}
import me.niccorder.shop.ItemDatastore.{CreateItem, DeleteItem, GetItem}
import javax.inject.Singleton

@Singleton
class ItemDatastoreController extends Controller with ItemDatastore.BaseServiceIface {

  val memoryStore: Map[String, Item] = Map()

  override val createItem = handle(CreateItem) { args =>
    Future.apply {
      memoryStore + (args.item.itemId -> args.item)
      args.item.itemId
    }
  }

  override val deleteItem = handle(DeleteItem) { args =>
    Future.exception(new NotImplementedError)
  }

  override val getItem = handle(GetItem) { args =>
    Future.exception(new NotImplementedError)
  }
}
