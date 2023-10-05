package org.muieer.scala.leetcode

/*
* https://leetcode.cn/problems/find-the-difference-of-two-arrays/
* */
object LeetCode2215 {

  def findDifference(nums1: Array[Int], nums2: Array[Int]): List[List[Int]] = {
    var res = List.empty[List[Int]]
    val map1 = nums1.toSet
    val map2 = nums2.toSet
    var list1 = List.empty[Int]
    var list2 = List.empty[Int]
    for {
      num <- nums1
    } {
      if (!map2.contains(num)) list1 = num :: list1
    }

    for {
      num <- nums2
    } {
      if (!map1.contains(num)) list2 = num :: list2
    }
    res = list2.distinct :: res
    res = list1.distinct :: res
    res
  }

}
