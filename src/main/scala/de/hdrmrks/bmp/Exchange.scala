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

import scroll.internal.Compartment
import annotations.Role

/**
 * Basic Compartment, which handles the Exchange of the given Resource between Sender and Receiver.
 * Inherit and implement it to use it with different types of connections and devices.
 *
 * @param resource the shareable Resource.
 *
 * @author Markus Heider (markus.heider@tu-dresden.de)
 */
abstract class Exchange(resource: Resource) extends Compartment {

  /**
   * You may use this Function to start the exchange of the given {@see Resource} between the Sender and Receiver.
   * To use it, inherit from this Compartment and override all necessary Functions, the way you want it to work.
   */
  def execute() ;

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
