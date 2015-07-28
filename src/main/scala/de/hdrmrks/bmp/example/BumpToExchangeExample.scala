package de.hdrmrks.bmp

import internal.Compartment
import annotations.Role

object BumpToExchangeExample extends App {

  val iPhone = new Device("iPhone6")
  val galS5  = new Device("Galaxy S5")

  new BumpPC {

    val resProv = new ResourceProvider()
    val resource = resProv.getResource()

    val exchange = new Exchange(resource)

    val receiver = new exchange.Receiver()
    val sender = new exchange.Sender()

    (iPhone.play(receiver))
    (galS5.play(sender))

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



