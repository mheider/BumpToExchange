package de.hdrmrks.bmp

case class Device(name : String) {
  override def toString(): String =  "Device: " + this.name
}
