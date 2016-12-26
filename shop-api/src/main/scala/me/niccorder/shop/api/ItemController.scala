package me.niccorder.shop.api

import com.google.inject.Inject
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import me.niccorder.shop.{Item, ItemDatastore}


class ItemController @Inject()(
  itemDatastore: ItemDatastore.FutureIface
) extends Controller {

  get("/item/:id") { req: Request =>
    val item: Item = itemDatastore.getItem(req.getIntParam("id")).asInstanceOf

    response.ok("{ hello: \"world\"")
  }
}
