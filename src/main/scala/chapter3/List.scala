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

  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B = as match {
    case Nil => z
    case Cons(x, xs) => f(x, foldRight(xs, z)(f))
  }

  def sum2(ns: List[Int]): Int = foldRight(ns, 0)((x, y) => x + y)
  def product2(ns: List[Double]): Double = foldRight(ns, 1.0)(_ * _)
  def length[A](as: List[A]):Int = foldRight(as, 0)((_, y) => 1 + y)

  @tailrec
  def foldLeft[A,B](as: List[A], z: B)(f: (B, A) => B): B = as match {
    case Nil => z
    case Cons(x, xs) => foldLeft(xs, f(z, x))(f)
  }

  def sum3(ns: List[Int]): Int = foldLeft(ns, 0)((x, y) => x + y)
  def product3(ns: List[Double]): Double = foldLeft(ns, 1.0)(_ * _)
  def length3[A](as: List[A]):Int = foldLeft(as, 0)((acc, _) => 1 + acc)


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

  @tailrec
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = {
    l match {
      case Cons(x, xs) if !f(x) => dropWhile(xs,f)
      case _ => l
    }
  }

  def apply[A](as: A*): List[A] =
    if(as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
}