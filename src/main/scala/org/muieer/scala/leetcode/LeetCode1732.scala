package org.muieer.scala.leetcode

object LeetCode1732 {

  def largestAltitude(gain: Array[Int]): Int = {
    var res = 0
    var cur = 0
    for (high <- gain) {
      cur = cur + high
      res = math.max(res, cur)
    }

    res
  }

}
