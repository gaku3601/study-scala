package com.gaku3601.studyscala
package chapter3

import scala.annotation.tailrec

sealed trait List[+A]

case object Nil extends List[Nothing]

case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

  def tail[A](l: List[A]): List[A] = l match {
    case Nil => Nil
    case Cons(_, xs) => xs
  }

  def setHead[A](head: A,l: List[A]): List[A] = l match {
    case Nil => Nil
    case Cons(_, xs) => Cons(head, xs)
  }

  @tailrec
  def drop[A](l: List[A], n: Int): List[A] =
    if(n <= 0) l
    else l match {
      case Nil => Nil
      case Cons(_, xs) => drop(xs, n-1)
    }

  def apply[A](as: A*): List[A] =
    if(as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
}

object Exercise2And3And4 {
  def main(args: Array[String]): Unit = {
    val list2 = List(1,2,3)
    println(List.tail(list2))
    val list3 = List(1,2,3)
    println(List.setHead(3, list3))
    val list4 = List(1,2,3)
    println(List.drop(list4, 1))
  }
}
