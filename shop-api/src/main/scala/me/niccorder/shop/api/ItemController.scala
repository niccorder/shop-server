package me.niccorder.shop.api

import com.google.inject.Inject
import com.twitter.finagle.ThriftMux
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.util.Await
import me.niccorder.shop.ItemDatastore
import me.niccorder.shop.ItemDatastore.CreateItem
import me.niccorder.shop.util.ItemJSON

class ItemController @Inject()(
    itemDatastore: ItemDatastoreClient
) extends Controller {

  get("/item/:id", name = "get_item") { req: Request =>
    val id: String = req.getParam("id")
    trace(s"$id was requested")

    try {
      response.ok.json(
        Await.result(
          itemDatastore.client.getItem(id.toInt)
        )
      )
    } catch {
      case t: Throwable => response.ok.json(t).response
    }
  }

  post("/item", name = "post_item") { req: Request =>
    response.notImplemented
  }
}
