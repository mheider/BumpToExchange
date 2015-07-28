package de.hdrmrks.bmp

import de.hdrmrks.bmp.example.ExchangeSocket
import internal.Compartment
import annotations.Role

object BumpToExchangeExample extends App {

  new BumpPC {

    val resProv = new ResourceProvider()
    val resource = resProv.getResource()

    val exchange = new ExchangeSocket(resource)

    val devices = new exchange.PeerDiscovery().lookupPeeringPatners();

    val receiver = new exchange.Receiver()
    val sender = new exchange.Sender()
    (devices(0).play(receiver))
    (devices(1).play(sender))

    exchange partOf(this)

    exchange.execute()
  }
}




class BumpPC extends Bump {

   class ResourceProvider extends super.ResourceProvider {
    override def getResource(): Resource = {
      val file = "/Users/markus/Desktop/hello.txt"
      val data = scala.io.Source.fromFile(file).map(_.toByte).toArray
      return new Resource(data, "hello.txt")
    }
  }

}



