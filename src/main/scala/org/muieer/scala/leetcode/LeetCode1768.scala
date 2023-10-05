package org.muieer.scala.leetcode

import scala.collection.mutable

/*
* https://leetcode.cn/problems/merge-strings-alternately/
* */
object LeetCode1768 {

  def main(args: Array[String]): Unit = {
    println(mergeAlternately("ab", "cipp"))
  }

  def mergeAlternately(word1: String, word2: String): String = {

    val res = new mutable.StringBuilder()
    var point = 0

    for {
      i <- 0 until word1.length
      if i < word1.length && i < word2.length
    } {
      res.append(word1.charAt(i)).append(word2.charAt(i))
      point = point + 1
    }

    if (point < word1.length) {
      res.append(word1.substring(point, word1.length))
    }
    if (point < word2.length) {
      res.append(word2.substring(point, word2.length))
    }

    res.toString()
  }

}
