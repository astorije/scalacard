package scalacard

case class Game(players: Vector[Player], deck: Deck)
case class Player(hand: Hand, props: Properties, cash: MoneyPile)

trait Card

trait Money extends Card
case object One extends Money
case object Two extends Money
case object Five extends Money
case object Ten extends Money



