package com.gaku3601.studyscala
package chapter3

object Exercise11 {
  def main(args: Array[String]): Unit = {
    val list1 = List(1,2,3)
    println(List.sum3(list1))
    val list2 = List(1.0,2.0,3.0,4.0)
    println(List.product3(list2))
    println(List.length3(list2))
  }
}
