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



