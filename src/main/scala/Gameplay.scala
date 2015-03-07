package scalacard

import scala.annotation.tailrec
import scala.util.Random

object Rules {

  val maxPlayers = 5
  val minPlayers = 2
  val dealSize = 5

}

object Gameplay {

  lazy val newDeck = List.fill(15)(One) ++
    	List.fill(15)(Two) ++
      List.fill(7)(Five) ++
      List.fill(4)(Ten)

  def newGame(players: Int) = {
    val ps = Vector.fill(players)(Player(Nil, propertyLess, Nil))

    Game(ps, Random.shuffle(newDeck))
  }

  def deal(g: Game): Game = {
    @tailrec
    def go(players: Vector[Player], d: Deck, acc: Int): Game = {
     if(acc == 0) Game(players, d)
     else {
       val p = players.head
       val np = p.copy(hand =  d.head +: p.hand)
       go(players.tail :+ np, d.tail, acc -1)
     }}
    go(g.players, g.deck, Rules.dealSize * g.players.length)
  }



}
