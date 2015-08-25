/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Markus H.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package de.hdrmrks.bmp

import org.scalatest._
import scroll.internal.Compartment

/**
 * Created by markus on 28.07.15.
 */
class ExchangeSpec extends FlatSpec with Matchers {

  val testResource = new Resource()

  val testDevice0 = new Device("iPhone")
  val testDevice1 = new Device("Galaxy S5")

  class TestExchange(resource: Resource) extends  Exchange(resource) {
    /**
     * You may use this Function to start the exchange of the given {@see Resource} between the Sender and Receiver.
     * To use it, inherit from this Compartment and override all necessary Functions, the way you want it to work.
     */
    override def execute(): Unit = {
      println("Executing exchange")
      val sender = one[Sender]()
      val receiver = one[Receiver]()
      println("Sending from " + (+sender).name + " to " + (+receiver).name)
      sender.send(receiver, resource)
    }


    class Receiver extends super.Receiver {
      override def receive(resource: Resource): Unit = {
        resource.equals(testResource) should be(true)
      }
    }

    class Sender extends super.Sender {
      def send(receiver: Receiver, res : Resource): Unit = {
        receiver should not be(null)
      }
    }

    class PeerDiscovery extends super.PeerDiscovery {
      override def lookupPeeringPatners(): List[Device] = List(testDevice0, testDevice1);
    }
  }

  "Exchange" should "send from sender to Receiver" in {
    new Bump {
      val testExchange = new TestExchange(testResource)
      val devices = new testExchange.PeerDiscovery().lookupPeeringPatners()
      val sender = new testExchange.Sender
      val receiver = new testExchange.Receiver

      devices(0).play(sender)
      devices(1).play(receiver)
    }
  }

  "PeerDiscovery" should "return testDevices" in {
    val testExchange = new TestExchange(testResource)
    val devices = new testExchange.PeerDiscovery().lookupPeeringPatners()
    devices(0) should be(testDevice0)
    devices(1) should be(testDevice1)
  }

}
