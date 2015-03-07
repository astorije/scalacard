package scalacard

import scala.annotation.tailrec
import scala.util.Random

object Rules {

  val maxPlayers = 5
  val minPlayers = 2
  val dealSize = 5

}

object Gameplay {

  lazy val newDeck = List.fill(6)(One) ++
    	List.fill(5)(Two) ++
      List.fill(3)(Three) ++
      List.fill(3)(Four) ++
      List.fill(2)(Five) ++
      (Ten :: Nil)

  def newGame(players: Int) = {
    val ps = Vector.fill(players)(Player(Nil, propertyLess, Nil))

    Game(ps, shuffle(newDeck))
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

  private def shuffle(d: Deck): Deck = Random.shuffle(d)

  def playTurn(g:Game): Game = {
    //draw 2 cards, unless hand is empty, in which case 5 cards

    //Play 0-3 cards

    // If > 7 cards, discard excess (allow player choice or random?)

    // End Turn

    //recurse infinitely until hitting fixed-point
    g
  }

  def endTurn(g: Game): Game = ???

}
