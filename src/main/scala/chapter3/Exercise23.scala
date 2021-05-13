package com.gaku3601.studyscala
package chapter3

object Exercise23 {
  def main(args: Array[String]): Unit = {
    val list1 = List(1,2,3)
    val list2 = List(1,2,3)
    println(List.zipWith(list1, list2)(_ + _))
  }
}
