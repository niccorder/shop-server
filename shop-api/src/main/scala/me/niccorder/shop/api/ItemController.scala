package me.niccorder.shop.api

import com.twitter.finagle.Service
import com.twitter.finatra.thrift.Controller
import com.twitter.util.{Await, Future}
import me.niccorder.shop.ItemDatastore
import me.niccorder.shop.ItemDatastore.{CreateItem, DeleteItem, GetItem}


class ItemController extends Controller with ItemDatastore.BaseServiceIface {

  override val createItem = handle(CreateItem) { args: CreateItem.Args =>
    Await.result(Future.exception(new Exception))
  }

  override val deleteItem = handle(DeleteItem) { args: DeleteItem.Args =>
    Await.result(Future.exception(new Exception))
  }

  override val getItem = handle(GetItem) { args: GetItem.Args =>
    Await.result(Future.exception(new Exception))
  }

}
