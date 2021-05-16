package com.gaku3601.studyscala
package chapter4

import chapter4.Exercise3.map2

object Exercise5 {
  def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = a match {
    case Nil => Some(Nil)
    case h :: t => map2(f(h), traverse(t)(f))(_ :: _)
  }

  def main(args: Array[String]): Unit = {
    print(traverse(List(Some(1.0),Some(2.0)))(x => x))
  }
}
