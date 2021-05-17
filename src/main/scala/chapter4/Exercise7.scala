package com.gaku3601.studyscala
package chapter4

import chapter4.Exercise3.map2

object Exercise7 {
  def traverse[E,A,B](as:List[A])(f:A=>Either[E,B]):Either[E,List[B]] = as match {
    case Nil => Right(Nil)
    case h :: t => (f(h) map2 traverse(t)(f))(_ :: _)
  }

  def sequence[E,A](es:List[Either[E,A]]):Either[E,List[A]] = traverse(es)(x => x)
}
