package com.gaku3601.studyscala
package chapter5

object Exercise10 {
  def fibs(): Stream[Int] = {
    def loop(n: Int, m: Int): Stream[Int] = Stream.cons(n , loop(n + m, n))
    loop(0, 1)
  }
}
