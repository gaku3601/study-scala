package com.gaku3601.studyscala
package chapter6

trait RNG{
  def nextInt:(Int,RNG)
}

case class SimpleRNG(seed:Long) extends RNG{
  def nextInt:(Int,RNG)={
    val newSeed=(seed*0x5DEECE66DL+0xBL)&0xFFFFFFFFFFFFL
    val nextRNG=SimpleRNG(newSeed)
    val n=(newSeed>>>16).toInt
    (n,nextRNG)
  }
}