package scalacard

case class Game(players: Vector[Player], deck: Deck)
case class Player(hand: Hand, props: Properties, cash: MoneyPile)

trait Card

trait Money extends Card
case object One extends Money
case object Two extends Money
case object Three extends Money
case object Four extends Money
case object Five extends Money
case object Ten extends Money

trait Action extends Card
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

trait Rent extends Card
case object DarkBlueGreen extends Rent
case object RedYellow extends Rent
case object PinkOrange extends Rent
case object LightBlueBrown extends Rent
case object RailroadUtility extends Rent
case object Wild extends Rent

trait Property extends Card
case object Blue extends Property
case object Utility extends Property
case object Green extends Property
case object Yellow extends Property
case object Orange extends Property
case object Pink extends Property
case object LightBlue extends Property
case object Railroad extends Property
case object Brown extends Property

trait PropertyWildcard extends Card
case object PropDarkBlueGreen extends PropertyWildcard
case object UtilityRailroad extends PropertyWildcard
case object LightBlueRailroad extends PropertyWildcard
case object PropLightBlueBrown extends PropertyWildcard
case object PropPinkOrange extends PropertyWildcard
case object PropRedYellow extends PropertyWildcard
case object MultiColor extends PropertyWildcard
case object GreenRailroad extends PropertyWildcard
