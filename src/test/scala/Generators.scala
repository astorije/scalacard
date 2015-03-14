package scalacard

import org.scalacheck.Gen

object Generators  {

  def newPlayer =
    Gen.const(Player(Nil, propertyLess, Nil))

  def game: Gen[Game] = for {
    i <- Gen.oneOf(2,3,4,5)
    players <- Gen.containerOfN[Vector, Player](i, rPlayer)
  } yield Game(players, Gameplay.newDeck, Nil)

  def rPlayer = for {
    hand <-  hand
    props <- properties
    cash <- moneyCards
  } yield Player(hand, props, cash)

  def properties: Gen[Properties] = Gen.listOf(property).map(_.toMap)
  def property = for {
    n <- Gen.choose(1, 3)
    p <- Gen.oneOf(propertyCard, propertyWildcardCard)
  } yield (p, n)

  def hand = for{
    i <- Gen.choose(0, 15)
    cs <- Gen.listOfN[Card](i, card)
  } yield cs

  def cards = Gen.listOf(card)
  def card =
      Gen.oneOf(moneyCard, actionCard, rentCard, propertyCard, propertyWildcardCard)

  def moneyCards = Gen.listOf(moneyCard)
  def moneyCard =
    Gen.oneOf(One, Two, Three, Four, Five, Ten)

  def actionCard =
    Gen.oneOf(SlyDeal, DealBreaker, JustSayNo, PassGo, ForcedDeal, DebtCollectors,
      ItsMyBirthday, House, Hotel, DoubleTheRent)

  def rentCard =
    Gen.oneOf(DarkBlueGreen, RedYellow, PinkOrange, LightBlueBrown, RailroadUtility, Wild)

  def propertyCard =
    Gen.oneOf(Blue, Utility, Green, Yellow, Orange, Pink, LightBlue, Railroad, Brown)

  def propertyWildcardCard =
    Gen.oneOf(PropDarkBlueGreen, UtilityRailroad, LightBlueRailroad, PropLightBlueBrown,
      PropPinkOrange, PropRedYellow, MultiColor, GreenRailroad)

}
