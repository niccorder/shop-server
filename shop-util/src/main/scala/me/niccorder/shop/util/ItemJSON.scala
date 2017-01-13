package me.niccorder.shop.util

import java.io.IOException

import com.fasterxml.jackson.databind.ObjectMapper
import me.niccorder.shop.{Item, ItemDatastore}

object ItemJSON {

  class DecodeException extends IOException

  def toJSON(item: Item): String = {
    new ObjectMapper().writeValueAsString(item)
  }

  def fromJSON(json: String): Item = Item.apply("1", "cheese", 42.0, Option.empty)
}
