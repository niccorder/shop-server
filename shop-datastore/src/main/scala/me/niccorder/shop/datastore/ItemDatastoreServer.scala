package me.niccorder.shop.datastore

import java.io.File

import com.twitter.finagle.{Dtab, ListeningServer, Thrift}
import com.twitter.server.TwitterServer
import com.twitter.util.Await
import me.niccorder.shop.ItemDatastore

object ItemDatastoreServerMain extends ItemDatastoreServer

class ItemDatastoreServer extends TwitterServer {

  implicit val serviceDest = flag("service.dest", "/s/item-datastore", "The item datastores path")

  override val name: String = "Item Datastore"

  def startService(): Unit = {
    log.debug("Starting service--------")

    Thrift.newServiceIface[ItemDatastore.ServiceIface](serviceDest.apply, "item-datastore-service")
  }

  startService()
}
