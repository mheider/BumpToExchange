package de.hdrmrks.bmp;

import internal.Compartment
import annotations.Role


object BumpToExchangeExample extends App {

  /** Simple class to hold our Resource, we want to share. */
  case class Resource(data: Array[Byte], name: String)

  class Device(name : String) {
    override def toString(): String =  "Device: " + this.name
  }

  class Bump extends Compartment {

    class RessourceProvider {
      def getResource() : Resource = {
        val file = "/Users/markus/Desktop/hello.txt"
        val data = scala.io.Source.fromFile(file).map(_.toByte).toArray
        return new Resource(data, "hello.txt")
      }
    }

  }


  class Exchange(resource: Resource) extends Compartment {

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

  }

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

