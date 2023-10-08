package org.muieer.scala.leetcode

import scala.collection.mutable

object LeetCode20 {

  def main(args: Array[String]): Unit = {
    println(isValid("()())"))
  }

  def isValid(s: String): Boolean = {
    if (s.length % 2 == 1) return false
    val map = Map('(' -> ')', '{' -> '}', '[' -> ']')
    val stack = new mutable.Stack[Char]()

    for {
      i <- 0 until s.length
      char = s(i)
    } {
      if (map.contains(char)) {
        stack.push(map(char))
      } else {
        if (stack.isEmpty || stack.pop() != char) {
          return false
        }
      }
    }

    stack.isEmpty
  }

}
