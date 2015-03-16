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
        val c = selectCard(g.player1.hand)
        Gameplay.playCard(c, g, choosePlayAsMoney(c))
      case EndTurn => Gameplay.advanceTurn(g)
      case Quit => g.copy(over = true)
    }
  }

  def showTable(g: Game): Unit
  def showHand(h: Hand): Unit
  def selectCard(h: Hand): Card
  def choosePlayAsMoney(c: Card): Boolean

}
