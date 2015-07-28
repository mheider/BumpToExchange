package de.hdrmrks.bmp.example

import de.hdrmrks.bmp.{Resource, Exchange}

/**
 * Created by markus on 28.07.15.
 */
class ExchangeSocket(resource: Resource) extends Exchange(resource) {

  override def execute(): Unit = {
    println("ExchangeSocket executing exchange")
    val sender = one[Sender]()
    val receiver = one[Receiver]()
    println("Sending from " + (+sender).name + " to " + (+receiver).name)
    sender.send(receiver, resource)
  }

  class Receiver extends super.Receiver {

  }

  class Sender extends super.Sender {

  }

}
