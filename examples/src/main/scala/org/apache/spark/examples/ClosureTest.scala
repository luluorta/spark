<<<<<<< HEAD
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.spark.examples

=======
package org.apache.spark.examples


>>>>>>> 36777187422b0d42bd82b115d600f3f42790b618
import _root_.org.apache.spark.SparkConf
import _root_.org.apache.spark.serializer.JavaSerializer
import _root_.org.apache.spark.util.ClosureCleaner

/**
 * Created by luluorta on 14-8-3.
 */

<<<<<<< HEAD
private[spark] class ClosureConstructor(private[ClosureConstructor] val func: () => Unit)
  extends Serializable {
=======
private[spark] class ClosureConstructor(private[ClosureConstructor] val func: () => Unit) extends Serializable {
>>>>>>> 36777187422b0d42bd82b115d600f3f42790b618
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
<<<<<<< HEAD
    ClosureCleaner.clean(cc3.func)
=======
    //ClosureCleaner.clean(cc3.func)
>>>>>>> 36777187422b0d42bd82b115d600f3f42790b618
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
