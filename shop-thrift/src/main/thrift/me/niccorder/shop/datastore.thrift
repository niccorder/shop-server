include "item.thrift"

namespace java me.niccorder.shop
#@namespace scala me.niccorder.shop

service ItemDatastore {
  string createItem(1: item.Item item)
  string deleteItem(1: i32 id)
  item.Item getItem(1: i32 id)
}