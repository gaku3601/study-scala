package com.gaku3601.studyscala
package chapter4

object Exercise4 {
  def sequence[A](a: List[Option[A]]): Option[List[A]] = a match {
    case Nil => Some(Nil)
    case h :: t => h flatMap(hh => sequence(t) map (hh :: _))
  }
  def main(args: Array[String]): Unit = {
    print(sequence(List(Some(1.0),Some(2.0))))
  }
}
