package com.gaku3601.studyscala
package chapter4


object Exercise2 {
  def mean(xs: Seq[Double]): Option[Double] =
    if(xs.isEmpty) None
    else Some(xs.sum / xs.length)

  def variance(xs: Seq[Double]): Option[Double] = mean(xs) flatMap(m => mean(xs.map(x => math.pow(x - m, 2))))
  def main(args: Array[String]): Unit = {
    val list = Seq(1.0, 2.0, 3.0)
    print(variance(list))
  }
}
