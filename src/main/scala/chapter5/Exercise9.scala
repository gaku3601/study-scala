package com.gaku3601.studyscala
package chapter5

object Exercise9 {
  def from(n: Int): Stream[Int] = Stream.cons(n , from(n + 1))
}
