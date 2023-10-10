package org.muieer.scala.leetcode

object LeetCode704 {

  def main(args: Array[String]): Unit = {
    println(search(Array(1), 2))
  }

  def search(nums: Array[Int], target: Int): Int = {

    var low = 0;
    var high = nums.length - 1
    while (low <= high) {
      val mid = ((high - low) >> 1) + low
      if (nums(mid) == target) return mid
      else if (nums(mid) < target) {
        low = mid + 1
      } else {
        high = mid - 1
      }
    }

    -1
  }

}
