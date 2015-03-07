

package object scalacard {

	type Hand = List[Card]
  type Deck = List[Card]
  type Bank = List[Card]
  type Discarded = List[Card]
  type Properties = Map[Card, Int]
  type MoneyPile = List[Money]

  val propertyLess = Map[Card, Int]()

}
