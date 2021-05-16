package com.gaku3601.studyscala
package chapter4

object Exercise3 {
  def map2[A,B,C](a: Option[A], b: Option[B])(f: (A,B) => C): Option[C] = a flatMap (aa => b map (bb => f(aa, bb)))
  def main(args: Array[String]): Unit = {
    print(map2(Some(1),Some(2))(_ + _))
  }
}
