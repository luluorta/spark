package org.apache.spark.examples


import _root_.org.apache.spark.SparkConf
import _root_.org.apache.spark.serializer.JavaSerializer
import _root_.org.apache.spark.util.ClosureCleaner

/**
 * Created by luluorta on 14-8-3.
 */

private[spark] class ClosureConstructor(private[ClosureConstructor] val func: () => Unit) extends Serializable {
  def call() {
    func()
  }
}

private[spark] object ClosureConstructor {
  private[this] var i = 0

  def apply(): ClosureConstructor = {
    val f = () => println(i)
    val cc1 = new ClosureConstructor(() => f())
    val cc2 = new ClosureConstructor(() => cc1.func())
    val cc3 = new ClosureConstructor(() => cc2.func())
    i += 3
    //ClosureCleaner.clean(cc3.func)
    val cc4 = new ClosureConstructor(cc3.func)
    i += 2
    cc4
  }
}

object ClosureTest {
  def main(args: Array[String]) {
    val rcc = ClosureConstructor()
    val ser = new JavaSerializer(new SparkConf()).newInstance()
    val cc = ser.deserialize[ClosureConstructor](ser.serialize(rcc))
    println("haha")
    cc.call()
  }
}
