package org.muieer.scala.leetcode

object LeetCode1207 {

  def uniqueOccurrences(arr: Array[Int]): Boolean = {
//    var map = Map.empty[Int, Int]
//    for (num <- arr) {
//      if (map.contains(num)) {
//        map = map + (num -> (map(num) + 1))
//      } else {
//        map = map + (num -> 1)
//      }
//    }
//    map.size == map.values.toSet.size
    arr.distinct.length == arr.groupBy(i=>i).map(_._2.length).toSet.size
  }

}
