package org.muieer.scala.leetcode

import scala.collection.mutable

object LeetCode409 {

  def main(args: Array[String]): Unit = {
    println(longestPalindrome("aaaaaccc"))
  }

  def longestPalindrome(s: String): Int = {
    if (s.length == 1) { 1 }
    else {
      var res = 0
      var haveOddNumber = false
      val map = mutable.HashMap.empty[Char, Int]

      for (char <- s) {
        if (map.contains(char)) {
          map.put(char, 1 + map(char))
        } else {
          map.put(char, 1)
        }
      }

      map.values.foreach(count => {
        if (count % 2 == 0) {
          res = res + count
        } else {
          res = res + count - 1
          haveOddNumber = true;
        }
      })

      if (haveOddNumber) {
        res + 1
      } else {
        res
      }
    }

  }
}
