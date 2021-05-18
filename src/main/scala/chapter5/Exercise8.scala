package com.gaku3601.studyscala
package chapter5

object Exercise8 {
  def constant[A](a: A): Stream[A] = Stream.cons(a, constant(a))
}
