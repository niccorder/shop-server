package me.niccorder.shop.datastore

import java.io.{File, FileInputStream, FileReader}
import javax.inject.Singleton

import com.google.inject.Provides
import com.twitter.finagle.thrift.ClientId
import com.twitter.finatra.thrift.modules.ClientIdWhitelistModule
import com.twitter.finatra.thrift.modules.ClientIdWhitelistModule.info
import com.twitter.inject.TwitterModule
import org.yaml.snakeyaml.Yaml

@Singleton
object ItemDatastoreModule extends TwitterModule {

  implicit val serviceDestFlag = flag("item.service.dest", "item-datastore-server", "The item datastores path")
  implicit val clientConfigFileFlag = flag("item.service.config", new File("/config.yml"), "File to the whitelisted clients.")

  @Provides
  @Singleton
  def providesWhitelist: Set[ClientId] = {
    val clientIds = new Yaml().
      load(new FileInputStream(clientConfigFileFlag.apply))
      .asInstanceOf[Set[String]]
      .map(_.toString)
      .map(ClientId.apply)

    info(s"Client id whitelist loaded ${clientIds.size} ids")
    clientIds
  }

}
