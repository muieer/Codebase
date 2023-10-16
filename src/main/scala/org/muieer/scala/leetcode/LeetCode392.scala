package org.muieer.scala.leetcode

object LeetCode392 {

  def main(args: Array[String]): Unit = {
    println(isSubsequence("aaaaaa", "bbaaaa"))
  }

  def isSubsequence(s: String, t: String): Boolean = {
    if (s.isEmpty) return true
    if (t.isEmpty) return false

    var si = 0
    var ti = 0

    while (si < s.length && ti < t.length) {
      // 依次匹配每个待匹配的字符
      while (ti < t.length && t(ti) != s(si)) {
        ti = ti + 1
      }
      // 匹配成功一个字符
      if (ti != t.length) {
        si = si + 1 // 指针右移
        ti = ti + 1 // 指针右移
      }
    }

    si == s.length
  }


}
