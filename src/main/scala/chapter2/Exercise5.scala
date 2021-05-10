package com.gaku3601.studyscala
package chapter2

object Exercise5 {
  private def compose[A,B,C](f: B =>C, g: A => B): A => C =
    (a: A) => f(g(a))

  def main(args: Array[String]): Unit = {
  }
}
