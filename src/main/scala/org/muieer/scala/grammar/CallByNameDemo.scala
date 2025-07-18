package org.muieer.scala.grammar

import scala.util.Random

// 参数按名传递，真正使用时才计算参数值
object CallByNameDemo {

  var flag = false

  def main(args: Array[String]): Unit = {
    println("第一次执行开始")
    var startTime = System.currentTimeMillis()
    lazyPrintln(take10SecondsGetString())
    println(s"第一次执行结束, 耗时: ${System.currentTimeMillis() - startTime} ms")
    flag = true
    println("第二次执行开始")
    startTime = System.currentTimeMillis()
    lazyPrintln(take10SecondsGetString())
    println(s"第二次执行结束, 耗时: ${System.currentTimeMillis() - startTime} ms")
  }

  // 懒执行 println
  def lazyPrintln(message: => String): Unit = {
    if (flag) {
      println(message)
    }
  }

  def take10SecondsGetString(): String = {
    Thread.sleep(10000)
    Random.nextString(10)
  }

}
