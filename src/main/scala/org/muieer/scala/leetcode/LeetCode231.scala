package org.muieer.scala.leetcode

object LeetCode231 {

  def main(args: Array[String]): Unit = {
    println(isPowerOfTwo(256))
  }

  def isPowerOfTwo1(n: Int): Boolean = {
    if (n < 1) false
    else {
      (n & (n -1)) == 0
    }
  }

  def isPowerOfTwo2(n: Int): Boolean = {
    if (n < 1) false
    else {
      var oneBitCount = 0
      var _n = n
      while(_n > 0) {
        oneBitCount = oneBitCount + 1
        _n = _n & (_n - 1)
      }
      oneBitCount == 1
    }
  }

  def isPowerOfTwo(n: Int): Boolean = {
    n > 0 && Integer.bitCount(n) == 1
  }

}
