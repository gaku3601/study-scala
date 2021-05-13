package com.gaku3601.studyscala
package chapter3

object Exercise18 {
  def main(args: Array[String]): Unit = {
    val list = List(1.0,2.0,3.0)
    println(List.map(list)(x => x.toInt))
  }
}
