package scalacard

import scala.annotation.tailrec
import scala.io.StdIn
import scala.util.Try

object Main {

  def main( args: Array[String]): Unit = {
  
    println("Welcome to ScalaCard, the world's greatest Monopoly Deal hobby project!")
    println("")
    println(s"How many players? [${Rules.minPlayers} -> ${Rules.maxPlayers}]")
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

  type PIn = Either[String, Int]
  private def validateIntInput: PIn = {
   (1 to 3).foldLeft[PIn](Left[String, Int]("abc"))((l, r) => {
       l match{
         case l@Right(_) => l
         case _ => {
           Try{ StdIn.readInt() }.toOption
             .fold[PIn]{
             println(s"Please enter a NUMBER between ${Rules.minPlayers}-${Rules.maxPlayers}")
             Left(s"Please enter a NUMBER between ${Rules.minPlayers}-${Rules.maxPlayers}")
           } { i =>
             if(i <= Rules.maxPlayers && Rules.minPlayers <= i) Right(i)
             else {
               println(s"$i was not between ${Rules.minPlayers} & ${Rules.maxPlayers}. Lets try to play be the rules.")
               Left(s"Looks like you don't really want to play...")
             }
           }}}
   })

   //@tailrec  //Note this is a hand rolled version of the reduction above
//   def go(tries: Int) : Either[String, Int] = {
//     tries match{
//       case 3 => Left("Looks like you don't really want to play...")
//       case _  => {
//        val in = StdIn.readInt()
//        if (in <=Rules.maxPlayers && in >= Rules.minPlayers) Right(in)
//        else {
//          go(tries +1)
//        }
//       }
//     }
//   }
 }

}
