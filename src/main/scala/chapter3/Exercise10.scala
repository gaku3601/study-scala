package com.gaku3601.studyscala
package chapter3

object Exercise10 {
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3)
    println(List.foldLeft(list, 0)(_ + _))
    // foldLeft(list(2,3), 1)(_ + _)
    // foldLeft(list(3), 3)(_ + _)
    // foldLeft(Nil, 6)(_ + _)
  }
}
