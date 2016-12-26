package me.niccorder.shop.util

import me.niccorder.shop.Item

trait ItemJSON {

  def toJSON(item: Item): String = "{ item:1, description:\"cheese\", price:42.0 }"

  def fromJSON(json: String): Item = Item.apply("1", "cheese", 42.0, Option.empty)
}
