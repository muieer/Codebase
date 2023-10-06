package org.muieer.scala.collection

object ListExample {

  def main(args: Array[String]): Unit = {
    immutableListDemo()
  }

  def immutableListDemo(): Unit = {

    var list = List.empty[Int]
    println(s"原始列表：$list")
    // 头插入一个元素
    list = 1 :: list
    println(s"列表头插入第一个元素：$list")
    list = 0 :: list
    println(s"列表头插入第二个元素：$list")
    list = list appended 2
    println(s"列表尾插入第一个元素：$list")
    println(s"输出列表索引 1 的元素：${list.indexOf(1)}")
  }
}
