package com.gaku3601.studyscala
package chapter6

object Exercise2 {
  def double(rng: RNG): (Double, RNG) = {
    val (i, r) = Exercise1.nonNegativeInt(rng)
    (i / (Int.MaxValue.toDouble + 1), r)
  }
}
