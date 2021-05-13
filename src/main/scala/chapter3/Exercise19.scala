package com.gaku3601.studyscala
package chapter3

object Exercise19 {
  def main(args: Array[String]): Unit = {
    val list = List(1.0,2.0,3.0)
    println(List.filter(list)(x => x % 2 == 0))
  }
}
