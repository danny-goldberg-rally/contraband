package com.foo

import java.util.Optional
import sjsonnew.JsonFormat
import sjsonnew.support.scalajson.unsafe.{ Converter, CompactPrinter }
import com.example._

object Example extends App {
  import generated.CustomProtocol._
  val g0: Greeting = SimpleGreeting.of("Hello")
  val g1: Greeting = SimpleGreeting.of("Hello", Optional.empty[Integer]())
  val g21: Greeting = GreetingWithAttachments.of("Hello", Array.empty)
  val g3: Greeting = GreetingWithOption.of("Hello", Optional.ofNullable("foo"))
  val gl1: GreetingList = GreetingList.of(Array(g0))
  val gl2: GreetingList = GreetingList.of(Array(g0))

  println(CompactPrinter(Converter.toJson(g0).get))
  println(Converter.fromJson[Greeting](Converter.toJson(g0).get).get)

  assert(Converter.fromJson[Greeting](Converter.toJson(g0).get).get == g0)
  assert(Converter.fromJson[Greeting](Converter.toJson(g1).get).get == g1)
  assert(Converter.fromJson[Greeting](Converter.toJson(g21).get).get == g21)
  assert(Converter.fromJson[Greeting](Converter.toJson(g3).get).get == g3)
  assert(gl1 == gl2)
  assert(gl1.## == gl2.##)
}
