package scalacard

import scala.io.StdIn

object Main {

  def main( args: Array[String]): Unit = {
  
    println("Welcome to ScalaCard, the world's greatest Monopoly Deal hobby project!")
    println("")
    println("How many players? [2 -> 5]")
    val n = validateIntInput
    val players = n.fold(e => {
      println(e)
      return
    }, l => {
      println(s"There are $l players.")
      l
    })

    println("Get Ready!")
    println(Gameplay.newGame(players))
  
  }

 private def validateIntInput: Either[String, Int] = {
   
   def go(tries: Int) : Either[String, Int] = {
     tries match{
       case 3 => Left("Looks like you don't really want to play...")
       case _  => {
        val in = StdIn.readInt()
        if (in <=5 && in >= 2) Right(in)
        else {
          println(s"$in was not between 2 & 5. Lets try to play be the rules.")
          go(tries +1)
        }
       }
     }
   }
   go(0)
 }

}
