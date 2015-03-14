//import scala.util.Random
//import scala.collection.immutable.Vector
//
//object Main {
////  def main(args: Array[String]): Unit = {
// //   val deck = Deck.newDeck
////
// //   val players: Vector[Player] = Vector(Player(Hand(Nil), Stack(Nil)))
////
// //   val s0 = Player.transition(Draw(2), Game(players, deck))
////
// //   println(Player.transition(Play(s0.players.head.hand.cards.head), s0))
//  //}
//}
//
//case class Card(value: Int)
//
//trait Action
//
//case class Draw(n: Int) extends Action
//case class Play(card: Card) extends Action
//
//case class Game(players: Vector[Player], deck: Deck)
//
//case class Player(hand: Hand, stack: Stack)
//
//object Player {
//  def transition(action: Action, game: Game): Game = action match {
//    case Draw(n) => {
//      val player = game.players.head
//      val cards = game.deck.cards take n
//      val deck = game.deck.cards drop n
//      val newPlayer = Player(Hand(player.hand.cards ++ cards), player.stack)
//      val newPlayers = newPlayer +: game.players.tail
//      Game(newPlayers, Deck(deck))
//    }
//    case Play(card) => {
//      val player = game.players.head
//
//      val newHand = Hand(player.hand.cards.foldLeft((List[Card](), true))((acc, c) =>
//        if (c.value == card.value && acc._2) (acc._1, false)
//        else (c :: acc._1, acc._2)
//      )._1)
//
//      val newStack = Stack(card :: player.stack.cards)
//
//      Game(Player(newHand, newStack) +: game.players.tail, game.deck)
//    }
//  }
//}
//
//case class Deck(cards: List[Card])
//
//case class Hand(cards: List[Card])
//
//case class Stack(cards: List[Card])
//
//object Deck {
//  def newDeck = Deck(
//    (1 to 3).toList.map { _ => Card(1) } ++
//    (1 to 3).toList.map { _ => Card(2) } ++
//    (Card(5) :: Nil)
//  )
//}
