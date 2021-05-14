package com.gaku3601.studyscala
package chapter3

object Exercise28 {
  def main(args: Array[String]): Unit = {
    val tree = Branch(Branch(Branch(Leaf(1), Leaf(2)), Leaf(3)), Leaf(2))
    println(Tree.map(tree)(_ + 1))
  }
}
