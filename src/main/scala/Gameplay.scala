package scalacard

import scala.annotation.tailrec
import scala.util.Random

object Rules {

  val maxPlayers = 5
  val minPlayers = 2
  val dealSize = 5

  val winCondition = (p: Properties) =>

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

  def playCard(c: Card, g: Game, useMoney: Boolean): Game = {
   import MoneyConversion._
    val game = if (useMoney) PlayMoneyCard(c, g.player1).build.run(g)._2
    else {
      c match{
        case x: Property =>
          PlayPropertyCard(x, g.player1).build.run(g)._2
        case _ => PlayMoneyCard(c, g.player1).build.run(g)._2
      }
    }
    val newPlayer = game.player1.copy(hand = removeFromHand(c, game.player1.hand))
    game.copy(players = newPlayer +: game.players.tail)
  }

  type RH = (List[Card], Boolean)
  private def removeFromHand(c: Card, h: Hand): Hand = {
    h.foldLeft[RH]((Nil, true))((acc, card) =>{
      if(card == c && acc._2) acc
      else (card :: acc._1, acc._2)
    })._1
  }

  def advanceTurn(g: Game): Game =
    g.copy(players = g.players.tail :+ g.players.head)



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
