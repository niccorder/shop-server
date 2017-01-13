package me.niccorder.shop.api

import com.google.inject.Inject
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.util.Await
import me.niccorder.shop.ItemDatastore
import me.niccorder.shop.util.ItemJSON

class ItemController @Inject()(
  itemDatastore: ItemDatastoreClient
) extends Controller {

  get("/item/:id", name = "get_item") { req: Request =>
    val id = req.getParam("id")
    trace(s"$id was requested")

    val itemRespFuture = itemDatastore.client.getItem(id.toInt)
    try {
      response.ok.json(Await.result(itemRespFuture))
    } catch {
      case t: Throwable => response.ok.json(t).response
    }
  }

  post("/item", name = "post_item") { req: Request =>
    response.notImplemented
  }
}
