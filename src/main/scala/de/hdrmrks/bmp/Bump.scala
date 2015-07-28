package de.hdrmrks.bmp

import internal.Compartment
import annotations.Role

class Bump extends Compartment {

  trait ResourceProviderTrait {
    def getResource():Resource;
  }

  class ResourceProvider extends ResourceProviderTrait{
    def getResource(): Resource = {
      return new Resource()

    }
  }
}
