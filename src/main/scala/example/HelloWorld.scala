package example

import scala.io.Source

@main def helloYou(args: String*) =
  val greeting = Source.fromResource("greeting.txt").getLines().mkString(" ")
  Console.println(s"$greeting ${args.mkString(", ")}")
