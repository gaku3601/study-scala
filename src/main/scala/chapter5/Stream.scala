package com.gaku3601.studyscala
package chapter5

import chapter5.Stream.empty

trait Stream[+A] {
  def toList: List[A] = this match {
    case Cons(h, t) => h() :: t().toList
  }
  def take(n: Int): Stream[A] = this match {
    case Cons(h, t) if n > 1 => Stream.cons(h(), t().take(n -1))
    case Cons(h, _) if n == 1 => Stream.cons(h(), empty)
    case _ => Empty
  }

  def drop(n: Int): Stream[A] = this match {
    case Cons(_, t) if n > 0 => t().drop(n -1)
    case _ => this
  }

  def takeWhile(p: A => Boolean): Stream[A] = this match {
    case Cons(h, t) if p(h()) =>  Stream.cons(h(), t() takeWhile p)
    case _ => empty
  }

  def foldRight[B](z: =>B)(f: (A,=>B)=>B): B = this match{
    case Cons(h,t)=>f(h(),t().foldRight(z)(f))
    case _=>z
  }

  def forAll(p:A=>Boolean):Boolean = foldRight(true)((a,b) => p(a) && b)
  def takeWhile2(p: A => Boolean): Stream[A] = foldRight(empty)((h, t) => if(p(h)) Stream.cons(h, t) else empty)
  def headOption: Option[A] =
    foldRight(None: Option[A])((h,_) => Some(h))
  def map[B](f: A => B): Stream[B] = foldRight(empty)((h, t) => Stream.cons(f(h), t))
  def filter(f: A => Boolean): Stream[A] = foldRight(empty)((h, t) => if(f(h)) Stream.cons(h, t) else t)
  def append[B >: A](s: Stream[B]): Stream[B] = foldRight(s)((h, t) => Stream.cons(h, t))
  def flatMap[B](f: A => Stream[B]): Stream[B] = foldRight(empty[B])((h, t) => f(h) append t)
}
case object Empty extends Stream[Nothing]
case class Cons[+A](h:()=>A,t:()=>Stream[A]) extends Stream[A]
object Stream {
  def cons[A](hd: => A,tl: =>Stream[A]): Stream[A] = {
    lazy val head= hd
    lazy val tail= tl
    Cons(()=>head,()=>tail)
  }
  def empty[A]:Stream[A] = Empty


  def apply[A](as:A*):Stream[A]=
    if(as.isEmpty) empty else cons(as.head, apply(as.tail:_*))
}
