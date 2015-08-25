/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Markus Heider
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

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
    override def lookupPeeringPartners(): List[Device] = {
      return List(new Device("iphone"), new Device("galaxyS6"))
    }
  }

}
