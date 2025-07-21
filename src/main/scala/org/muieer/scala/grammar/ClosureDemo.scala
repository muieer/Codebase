package org.muieer.scala.grammar

/*
* 闭包=函数+函数读写的自身作用域之外的变量
* */
object ClosureDemo {

  def main(args: Array[String]): Unit = {
    println("xPlusYFunc x is 1")
    val xPlus1Func = xPlusYFunc(1)
    println(s"xPlusYFunc result is ${xPlus1Func(1)}")
    println("xPlusDoubleYFunc x is 1")
    println(s"xPlusDoubleYFunc result is ${xPlusDoubleYFunc(2)(1)}")
  }

  def xPlusYFunc(y: Int): Int => Int = {
    val methodName = Thread.currentThread.getStackTrace()(1).getMethodName
    println(s"$methodName y is $y")
    (x: Int) => x + y //闭包
  }

  def xPlusDoubleYFunc(y: Int): Int => Int = {
    val methodName = Thread.currentThread.getStackTrace()(1).getMethodName
    println(s"$methodName y is $y")
    (x: Int) => x + 2 * y //闭包
  }
}
