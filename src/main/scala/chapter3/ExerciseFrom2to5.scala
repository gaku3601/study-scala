package com.gaku3601.studyscala
package chapter3

object ExerciseFrom2to5 {
  def main(args: Array[String]): Unit = {
    val list2 = List(1, 2, 3)
    println(List.tail(list2))
    val list3 = List(1, 2, 3)
    println(List.setHead(3, list3))
    val list4 = List(1, 2, 3)
    println(List.drop(list4, 1))
    val list5 = List(1, 2, 3)
    println(List.dropWhile(list5, (n: Int) => n == 3))
  }
}
