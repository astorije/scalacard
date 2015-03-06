package scalacard

trait Action

case class Draw(n: Int) extends Action
case object EndTurn extends Action
case class PlayCard(c: Card) extends Action




