package de.hdrmrks.bmp

import internal.Compartment
import annotations.Role

abstract class Exchange(resource: Resource) extends Compartment {

  def execute() {
    println("Executing exchange")
    val sender = one[Sender]()
    val receiver = one[Receiver]()
    println("Sending from " + (+sender).name + " to " + (+receiver).name)
    sender.send(receiver, resource)
  }

  @Role class Receiver() {
    def receive(res : Resource): Unit = {
      val dataStr = new String(res.data, "UTF-8")
      println("FileName: " + res.name + " ################")
      println("Content START #############################")
      print(dataStr)
      println("Content END ###############################")
    }
  }

  @Role class Sender() {
    def send(receiver: Receiver, res : Resource) = {
      receiver.receive(res)
    }
  }

  @Role abstract class PeerDiscovery() {
    def lookupPeeringPatners(): List[Device];
  }

}
