package com.gaku3601.studyscala
package chapter2

object Exercise3 {
  private def curry[A,B,C](f: (A,B) => C): A => B => C =
    (a: A) => (b: B) => f(a,b)

  def main(args: Array[String]): Unit = {
  }
}
