package com.gaku3601.studyscala
package chapter3

import org.omg.CORBA.Any

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

  def reverse[A](as: List[A]): List[A] = foldLeft(as, List[A]())((acc, x) => Cons(x, acc))
  // List(1,2,3) → List(3, 2, 1)
  // Cons(xs, Cons(1, Nil)) → Cons(xs, Cons(2, Cons(1, Nil)))

  def append[A](as: List[A], tg: List[A]): List[A] = foldRight(as, tg)((x, acc) => Cons(x, acc))
  // Cons(1, Nil) → Cons(1, Cons(2, Nil))

  def addOne(as: List[Int]): List[Int] = foldRight(as, Nil:List[Int])((x, acc) => Cons(x + 1, acc))

  def tail[A](l: List[A]): List[A] = l match {
    case Nil => Nil
    case Cons(_, xs) => xs
  }

  def toString(as: List[Double]):List[String] = foldRight(as, Nil: List[String])((x, acc) => Cons(x.toString, acc))

  def map[A,B](as: List[A])(f: A => B): List[B] = foldRight(as, Nil: List[B])((x, acc) => Cons(f(x), acc))

  def concat[A](l: List[List[A]]): List[A] = foldRight(l, Nil:List[A])(append)

  def filter[A](as: List[A])(f: A => Boolean): List[A] = foldRight(as, Nil: List[A])((x, acc) => if(f(x)) Cons(x, acc) else acc)

  def flatMap[A,B](as: List[A])(f: A => List[B]): List[B] = concat(map(as)(f))

  def filter2[A](as : List[A])(f: A => Boolean): List[A] = flatMap(as)(x => if(f(x)) List(x) else Nil)

  def add(as: List[Int], tg: List[Int]): List[Int] = (as,tg) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (Cons(x,xs), Cons(y, ys)) => Cons(x + y, add(xs, ys))
  }

  def zipWith[A,B,C](as: List[A], tg: List[B])(f: (A, B) => C): List[C] = (as,tg) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (Cons(x,xs), Cons(y, ys)) => Cons(f(x, y), zipWith(xs, ys)(f))
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
