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
    (Ten :: Nil) ++
    List.fill(2)(Blue) ++
    List.fill(2)(Utility) ++
    List.fill(3)(Green) ++
    List.fill(2)(Brown) ++
    List.fill(3)(Yellow) ++
    List.fill(3)(Red) ++
    List.fill(3)(Orange) ++
    List.fill(3)(Pink) ++
    List.fill(3)(LightBlue) ++
    List.fill(4)(Railroad)

  def newGame(players: Int) = {
    val ps = Vector.fill(players)(Player(Nil, propertyLess, Nil))

    Game(ps, shuffle(newDeck), Nil)
  }

  def deal(g: Game): Game = {
    @tailrec
    def go(players: Vector[Player], d: Deck, acc: Int): Game = {
     if(acc == 0) Game(players, d, Nil)
     else {
       val p = players.head
       val np = p.copy(hand =  d.head +: p.hand)
       go(players.tail :+ np, d.tail, acc -1)
     }}
    go(g.players, g.deck, Rules.dealSize * g.players.length)
  }

  private def shuffle(d: Deck): Deck = Random.shuffle(d)

//  def playTurn(g:Game): Game ={
//    val p = g.players.head
//    val n = if (p.hand.isEmpty) 5 else 2
//
//    for{
//      p2 <- Draw(n, p).build.run(g)
//      a = p2
//    } yield a
//  }
//
//
//
//  def doGameAction(c: ): () = {
//
//  }



  def endTurn(g: Game): Game = ???

}
