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

  //unfoldパターン
  def mapViaUnfold[B](f: A => B): Stream[B] = Exercise11.unfold(this){
    case Cons(h, t) => Some(f(h()), t())
    case _ => None
  }

  def takeViaUnfold(n: Int): Stream[A] =
    Exercise11.unfold((this,n)) {
      case (Cons(h,t), 1) => Some((h(), (empty, 0)))
      case (Cons(h,t), n) if n > 1 => Some((h(), (t(), n-1)))
      case _ => None
    }

  def takeWhileViaUnfold(p: A => Boolean): Stream[A] = Exercise11.unfold(this){
    case Cons(h, t) if p(h()) => Some(h(), t())
    case _ => None
  }

  def zipWith[B,C](s2: Stream[B])(f: (A,B) => C): Stream[C] =
    Exercise11.unfold((this, s2)) {
      case (Cons(h1,t1), Cons(h2,t2)) => Some((f(h1(), h2()), (t1(), t2())))
      case _ => None
    }

  def zipAll[B](s2: Stream[B]): Stream[(Option[A],Option[B])] = zipWithAll(s2)((_,_))
  def zipWithAll[B, C](s2: Stream[B])(f: (Option[A], Option[B]) => C): Stream[C] =
    Exercise11.unfold((this, s2)) {
      case (Empty, Empty) => None
      case (Cons(h, t), Empty) => Some(f(Some(h()), Option.empty[B]) -> (t(), empty[B]))
      case (Empty, Cons(h, t)) => Some(f(Option.empty[A], Some(h())) -> (empty[A] -> t()))
      case (Cons(h1, t1), Cons(h2, t2)) => Some(f(Some(h1()), Some(h2())) -> (t1() -> t2()))
    }

  def tails: Stream[Stream[A]] = Exercise11.unfold(this){
    case Empty => None
    case s => Some((s , s drop 1))
  } append Stream(empty)

  def scanRight[B](z: B)(f: (A, => B) => B): Stream[B] =
    foldRight((z, Stream(z)))((a, p0) => {
      lazy val p1 = p0
      val b2 = f(a, p1._1)
      (b2, Stream.cons(b2, p1._2))
    })._2
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
