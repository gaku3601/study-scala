package com.gaku3601.studyscala
package chapter4

object Exercise1 {
  def main(args: Array[String]): Unit = {
    val value1 = Some(2)
    println(value1.map(x => x + 1))
    val value2 = Some(2)
    println(value2.flatMap(x => Some(x)))
    val value3 = Some(3)
    println(value3.getOrElse(1))
    val value4 = None
    println(value4.orElse(Some(1)))
    val value5 = Some(2)
    println(value5.filter(x => x == 1))
  }
}
