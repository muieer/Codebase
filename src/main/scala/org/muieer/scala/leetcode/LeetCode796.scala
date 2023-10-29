package org.muieer.scala.leetcode

object LeetCode796 {

  def main(args: Array[String]): Unit = {
    println(rotateString("bbbacddceeb", "ceebbbbacdd"))
  }

  def rotateString(s: String, goal: String): Boolean = {

    var sHead = 0
    while (sHead < goal.length && goal(sHead) != s(0)) {
      sHead = sHead + 1
    }

    while (sHead < goal.length && !s.startsWith(goal.substring(sHead))) {
      sHead = sHead + 1
    }

    if (sHead == goal.length) { false }
    else {
      val rollBackGoal = goal.substring(sHead) + goal.substring(0, sHead)
      rollBackGoal == s
    }

  }

}
