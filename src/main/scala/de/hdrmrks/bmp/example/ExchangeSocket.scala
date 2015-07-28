package de.hdrmrks.bmp.example

import de.hdrmrks.bmp.{Device, Resource, Exchange}

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
    override def receive(res : Resource): Unit = {
      println("Receiving via socket")
      super.receive(res)
    }

  }

  class Sender extends super.Sender {

    def send(receiver: Receiver, res : Resource): Unit = {
      println("Sending via Socket")
      receiver.receive(res)
    }

  }

  class PeerDiscovery extends super.PeerDiscovery {
    override def lookupPeeringPatners(): List[Device] = {
      return List(new Device("iphone"), new Device("galaxyS6"))
    }
  }

}
