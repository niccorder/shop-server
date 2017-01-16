package me.niccorder.shop.api

import com.google.inject.Inject
import com.twitter.finagle.ThriftMux
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.util.Await
import me.niccorder.shop.{Item, ItemDatastore}
import me.niccorder.shop.ItemDatastore.CreateItem
import me.niccorder.shop.api.model.InMemoryItemDatastore
import me.niccorder.shop.util.ItemJSON

class ItemController @Inject()(
    itemDatastore: InMemoryItemDatastore
) extends Controller {

  get("/item/:id", name = "get_item") { req: Request =>
    val id: String = req.getParam("id")
    trace(s"$id was requested")

    try {
      response.ok.json(
        Await.result(
          itemDatastore.getItem(id.toInt)
        )
      )
    } catch {
      case t: Throwable =>
        response.internalServerError.json(Map("message" -> t.getMessage))
    }
  }

  get("/item", name = "get_item") { req: Request =>
    val id: String = req.getParam("id")
    trace(s"$id was requested")

    try {
      response.ok.json(
        Await.result(
          itemDatastore.getItems()
        )
      )
    } catch {
      case t: Throwable =>
        response.internalServerError.json(Map("message" -> t.getMessage))
    }
  }


  post("/item", name = "post_item") { req: Request =>
    try {
      response.ok.json(
        Await.result(
          itemDatastore.createItem(
            Item.apply(
              req.getParam("id"),
              req.getParam("name"),
              req.getParam("price").toDouble,
              Option(req.getParam("description"))
            )
          )
        )
      )
    } catch {
      case t: Throwable =>
        response.internalServerError.json(Map("message" -> t.getMessage))
    }
  }

  put("/item", name = "put_item") { req: Request =>
    try {
      response.ok.json(
        Await.result(
          itemDatastore.createItem(
            Item.apply(
              req.getParam("id"),
              req.getParam("name"),
              req.getParam("price").toDouble,
              Option(req.getParam("description"))
            )
          )
        )
      )
    } catch {
      case t: Throwable =>
        response.internalServerError.json(Map("message" -> t.getMessage))
    }
  }
}
