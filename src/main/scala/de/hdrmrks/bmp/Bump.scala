package de.hdrmrks.bmp

import internal.Compartment
import annotations.Role

class Bump extends Compartment {

  class RessourceProvider {
    def getResource() : Resource = {
      val file = "/Users/markus/Desktop/hello.txt"
      val data = scala.io.Source.fromFile(file).map(_.toByte).toArray
      return new Resource(data, "hello.txt")
    }
  }

}
