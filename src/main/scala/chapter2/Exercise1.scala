package com.gaku3601.studyscala
package chapter2

import scala.annotation.tailrec

object Exercise1 {
  private def fib(n: Int):Int = {
    @tailrec
    def loop(n: Int, acc: Int, next: Int):Int = {
      if(n <= 0 ) acc
      else loop(n-1, acc+next,acc)
    }
    loop(n, 0, 1)
  }


  def main(args: Array[String]): Unit =
    println(fib(15))
}
