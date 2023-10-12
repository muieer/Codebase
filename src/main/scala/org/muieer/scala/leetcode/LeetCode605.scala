package org.muieer.scala.leetcode

import scala.util.control.Breaks.break

object LeetCode605 {

  def canPlaceFlowers(flowerbed: Array[Int], n: Int): Boolean = {

    var target = n
    var firstZeroIndex = 0

    while (n > 0 && firstZeroIndex < flowerbed.length) {
      while (firstZeroIndex < flowerbed.length && flowerbed(firstZeroIndex) == 1) {
        firstZeroIndex += 1
      }
      if (firstZeroIndex == flowerbed.length) break
      if (firstZeroIndex + 2 < flowerbed.length) {

      }
    }

    target == 0
  }

}
