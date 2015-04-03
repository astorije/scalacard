package scalacard

trait MonopolyDrawCmd
case object ShowTable extends MonopolyDrawCmd
case object ShowHand extends MonopolyDrawCmd
case object PlayHandCard extends MonopolyDrawCmd
case object EndTurn extends MonopolyDrawCmd
case object Quit extends MonopolyDrawCmd

trait InputInterpreter {
  def interpret(cmd: MonopolyDrawCmd, g: Game): Game  = {
    cmd match{
      case ShowTable =>
        showTable(g)
        g
      case ShowHand =>
        showHand(g.player1.hand)
        g
      case PlayHandCard =>
        selectCard(g.player1.hand) match {
          case None => g
          case Some(card) => Gameplay.playCard(card, g, choosePlayAsMoney)
        }
      case EndTurn => Gameplay.advanceTurn(g)
      case Quit => g.copy(over = true)
    }
  }

  def showTable(g: Game): Unit
  def showHand(h: Hand): Unit
  def selectCard(h: Hand): Option[Card]
  def choosePlayAsMoney(): Boolean
}
