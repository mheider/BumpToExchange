package de.hdrmrks.bmp

import internal.Compartment
import annotations.Role

object BumpToExchangeExample extends App {

  val iPhone = new Device("iPhone6")
  val galS5  = new Device("Galaxy S5")

  new Bump {

    val resProv = new RessourceProvider()
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




