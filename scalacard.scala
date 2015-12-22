// object ScalaCard {
//   def main(args: Array[String]) {

//     // Game(List(Player(Hand(Set()), Stack(Set()))))


//     val g = Game.startGame()

//     Game.play(g)
//     // println(g)

//     // val g2 = g.copy(player = g.player.play(g.player.hand.cards.head))
//     // println(g2)

//     // val g3 = g2.copy(player = g2.player.play(g2.player.hand.cards.head))
//     // println(g3)
//   }
// }

// case class Card(value: Int) {
//   // assert(v >= 0 && v <= 10, s"v = $v: must be between 0 and 10")

//   override def toString(): String = "(" + value + ")"
// }

// // 1 player for now, 3 actions max par turn
// // A turn always starts with the player drawing 2 cards
// // An action for now is just "play" (moving a card from his Hand to his Stack)
// case class Game(deck: Deck, players: List[Player], winningPlayer: Option[Player]) {
//   def deal(): Game = {
//     def go(ps: List[Player], d: Deck, passes: Int): (List[Player], Deck, Int) = {
//       if (passes == 0) (ps, d, 0) else {
//         ps match{
//           case h :: t => {
//            val (nd, np) = h.draw(d)
//             go( (np :: t.reverse).reverse, nd, passes - 1)
//           }
//           case Nil => ???
//         }
//       }
//      go(ps, d, ps.length * 5)
//     }
//   }

//   override def toString(): String = {
//     "---\n" +
//     "Deck: " + deck + "\n" +
//     "Players:\n" + players +
//     "---\n"
//   }
// }

// object Game {
//   // Initialize a deck, shuffle it, deal 2 cards to player, set the remaining actions of the player to 3
//   def startGame(): Game = {
//     val d = Deck(List(Card(1), Card(2), Card(3), Card(4), Card(5), Card(6))).shuffle
//     val p = Player(Hand(Set()), Stack(Set()))
//     Game(d, List(p), None)
//   }

//   def play(game: Game): Game = {
//     game.players match {
//       case h :: t => {
//         val r = Turn.playTurn(Some(Turn(h, 3)), game)
//         r match {
//           case (None, Game(_, _, Some(p))) => r._2
//           case (None, Game(_, h :: t, _)) => play(r._2.copy(players = (h :: t.reverse).reverse))
//         }
//       }
//     }
//   }
// }

// case class Deck(cards: List[Card]) {
//   def shuffle(): Deck = Deck(util.Random.shuffle(cards))

//   override def toString(): String = cards.foldLeft("")(_ + _ + " ")
// }

// // type or [case] class?
// case class Hand(cards: Set[Card]) {
//   override def toString(): String = cards.foldLeft("")(_ + _ + " ")
// }
// case class Stack(cards: Set[Card]) {
//   // Return the total value of a stack
//   def total(): Int = cards.foldLeft(0)(_ + _.value)

//   override def toString(): String = cards.foldLeft("")(_ + _ + " ")
// }

// case class Turn(player: Player, remainingActions: Int) {
//   assert(remainingActions >= 0 && remainingActions <= 3, "Players have 0 to 3 actions allowed per turn")


// }

// object Turn {
//   def playTurn(turn: Option[Turn], game: Game): (Option[Turn], Game) = turn match {
//     case Some(Turn(_, 0)) => (None, game)
//     case Some(Turn(p, n)) => {
//       val nP = p.play(p.hand.cards.head)
//       nP.stack.total match {
//         case n if n > 20 => playTurn(Some(Turn(nP, n - 1)), game.copy(winningPlayer = Some(nP)))
//         case _ => playTurn(Some(Turn(nP, n - 1)), game)
//       }
//     }
//   }
// }


// case class Player(hand: Hand, stack: Stack) {

//   def drawN(deck: Deck, n: Int): (Deck, Player) = ???
//   //   if (n > 0) {
//   //     val (d, p) = this.draw(deck)
//   //     drawN(, n - 1)
//   //   }

//   // Draw a card from the deck and put it in the player's hand
//   def draw(deck: Deck): (Deck, Player) = deck.cards match {
//     case Nil => (deck, this)
//     case h :: t => (Deck(t), this.copy(hand = Hand(hand.cards + h)))
//   }

//   // Move a card from the player's hand to his and decrease his remaining actions
//   def play(card: Card): Player =
//     if (hand.cards.contains(card))
//       Player(Hand(hand.cards - card), Stack(stack.cards + card))
//     else this

//   override def toString(): String = {
//     "  Hand = " + hand + "\n" +
//     "  Stack = " + stack + " (total = " + stack.total + ")\n"
//     // "  Remaining actions = " + remainingActions + "\n"
//   }
// }

// // def moveCard(card: Card, from: Set[Card], to: Set[Card]): (Set[Card], Set[Card]) = {
// //   if (from contains card)
// //     (from - card, to + card)
// //   else
// //     (from, to)
// // }
