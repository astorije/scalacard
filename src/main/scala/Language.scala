package scalacard

trait GameAction

case class Draw(n: Int) extends GameAction
case object EndTurn extends GameAction
case class PlayCard(c: Card) extends GameAction




