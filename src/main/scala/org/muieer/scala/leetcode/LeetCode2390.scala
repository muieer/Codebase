package org.muieer.scala.leetcode

object LeetCode2390 {

  def removeStars(s: String): String = {
    var res = ""
    val stack = new scala.collection.mutable.Stack[Char]

    for {
      char <- s
    } {
      if (char == '*') {
        stack.pop()
      } else {
        stack.push(char)
      }
    }

    while (stack.nonEmpty) {
      res = res + stack.pop()
    }

    res.reverse
  }

}
