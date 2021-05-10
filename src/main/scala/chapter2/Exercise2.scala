package com.gaku3601.studyscala
package chapter2

import scala.annotation.tailrec

object Exercise2 {
  private def isSorted[A](as: Array[A], ordered:(A,A) => Boolean) : Boolean = {
    @tailrec
    def loop(n: Int): Boolean =
      if(n >= as.length) true
      else if (!ordered(as(n-1), as(n))) false
      else loop(n+1)

    loop(1)
  }



  def main(args: Array[String]): Unit = {
    val arr = Array(1)
    val order: (Int, Int) => Boolean = (n,m) => n <= m

    print(this.isSorted(arr, order))
  }
}
