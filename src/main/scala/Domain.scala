package scalacard

case class Game(players: Vector[Player], deck: Deck, discarded: Discarded, over: Boolean = false){
  def player1 = players.head
}

case class Player(name: String, hand: Hand, props: Properties, cash: MoneyPile)

object Player {
  import MoneyConversion._
  def moneyValue(p: Player): Int =
    p.cash.foldLeft(0)((i, m) => i + m)
}

sealed trait Card

sealed trait Money extends Card
case object One extends Money
case object Two extends Money
case object Three extends Money
case object Four extends Money
case object Five extends Money
case object Ten extends Money
case object Zero extends Money

sealed trait Action extends Card
case object SlyDeal extends Action
case object DealBreaker extends Action
case object JustSayNo extends Action
case object PassGo extends Action
case object ForcedDeal extends Action
case object DebtCollectors extends Action
case object ItsMyBirthday extends Action
case object House extends Action
case object Hotel extends Action
case object DoubleTheRent extends Action

sealed trait Rent extends Card
case object DarkBlueGreen extends Rent
case object RedYellow extends Rent
case object PinkOrange extends Rent
case object LightBlueBrown extends Rent
case object RailroadUtility extends Rent
case object Wild extends Rent

sealed trait Property extends Card
case object Blue extends Property
case object Utility extends Property
case object Green extends Property
case object Yellow extends Property
case object Red extends Property
case object Orange extends Property
case object Pink extends Property
case object LightBlue extends Property
case object Railroad extends Property
case object Brown extends Property

sealed trait PropertyWildcard extends Property
case object PropDarkBlueGreen extends PropertyWildcard
case object UtilityRailroad extends PropertyWildcard
case object LightBlueRailroad extends PropertyWildcard
case object PropLightBlueBrown extends PropertyWildcard
case object PropPinkOrange extends PropertyWildcard
case object PropRedYellow extends PropertyWildcard
case object MultiColor extends PropertyWildcard
case object GreenRailroad extends PropertyWildcard

object MoneyConversion {
  implicit def asMoney(c: Card): Money =
    c match {
       case a: Action => actions(a)
       case p: Property => properties(p)
       case r: Rent => rent(r)
       case m: Money => m
    }

  private def actions(a: Action): Money =
    a match {
      case SlyDeal => Three
      case House => Three
      case ItsMyBirthday => Two
      case PassGo => One
      case JustSayNo => Four
      case Hotel => Four
      case DealBreaker => Five
      case DebtCollectors => Three
      case DoubleTheRent => One
      case ForcedDeal => Three
    }

  private def properties(p: Property): Money =
    p match {
      case Blue | Green | PropDarkBlueGreen | LightBlueRailroad | GreenRailroad => Four
      case Utility | Railroad | Pink | Orange | UtilityRailroad | PropPinkOrange => Two
      case Yellow | Red | PropRedYellow => Three
      case LightBlue | Brown | PropLightBlueBrown => One
      case MultiColor => Zero
    }

  private def rent(r: Rent): Money =
    r match {
      case LightBlueBrown | DarkBlueGreen | PinkOrange | RailroadUtility | RedYellow => One
      case Wild => Three
    }

  implicit def moneyAsInt(m: Money): Int =
    m match {
      case Zero => 0
      case One => 1
      case Two => 2
      case Three => 3
      case Four => 4
      case Five => 5
      case Ten => 10
    }

}
