package org.muieer.scala.clazz

case class Person(name: String, age: Int)

//object Person {
//  def apply(name: String, age: Int): Person = new Person(name, age)
//}

object Main {

  def main(args: Array[String]): Unit = {
    val p1 = Person("muieer", 18)
    val p2 = Person.tupled(("muieer", 18))
    println(p1==p2)
  }
}
