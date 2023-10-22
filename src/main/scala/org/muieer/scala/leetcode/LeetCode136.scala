package org.muieer.scala.leetcode

class LeetCode136 {

  def singleNumber(nums: Array[Int]): Int = {
    var res = 0
    for (num <- nums) {
      res = res ^ num // 任何数与 0 异或其值都不变
    }
    res
  }

}
