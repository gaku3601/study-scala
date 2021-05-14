package com.gaku3601.studyscala
package chapter3

object Exercise26 {
  def main(args: Array[String]): Unit = {
    val tree = Branch(Branch(Leaf(1), Leaf(3)), Leaf(2))
    println(Tree.maximum(tree))
  }
}
