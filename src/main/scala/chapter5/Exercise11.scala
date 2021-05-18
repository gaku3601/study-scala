package com.gaku3601.studyscala
package chapter5

object Exercise11 {
  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = f(z) match {
    case Some((a, s)) => Stream.cons(a, unfold(s)(f))
    case None => Stream.empty
  }

  val fibsViaUnfold: Stream[Int] =
    unfold((0, 1)){ case (f0,f1) => Some((f0,(f1,f0+f1))) }
    // or (f => Some((f._1,(f._2,f._1+f._2))))

  def from(n: Int): Stream[Int] = unfold(n)(n => Some(n, n + 1))

  def constant[A](a: A): Stream[A] = unfold(a)(_ => Some(a, a))

  val onesViaUnfold: Stream[Int] = unfold(1)(_ => Some((1,1)))
}
