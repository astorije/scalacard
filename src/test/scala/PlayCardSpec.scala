package scalacard

import org.scalacheck.{Properties => SProp}
import org.scalacheck.Prop._
import Generators._

object PlayCardSpec extends SProp("PlayCard"){

  property("Playing Money increases a player's money stack by the card amount") = forAll(moneyCard, game)((c, g) => {
    import MoneyConversion._
    val p = g.players.head
    val (pA, gA) = PlayMoneyCard(c, p).build.run(g)

    Player.moneyValue(p) + c == Player.moneyValue(pA)
  })

  property("All cards may be played as money") = forAll(card, game)((c, g) => {
   import MoneyConversion._
   val p = g.players.head

    val m: Money = c
    val (pA, gA) = PlayMoneyCard(c, p).build.run(g)

    Player.moneyValue(p) + m == Player.moneyValue(pA)
  })

  property("Only current player's money is increased") = forAll(card, game)((c, g) => {
    import MoneyConversion._
    val p = g.players.head

    val (pA, gA) = PlayMoneyCard(c, p).build.run(g)
    g.players.tail.zip(gA.players.tail).forall(t => Player.moneyValue(t._1) == Player.moneyValue(t._2))
  })


  property("Playing a property card increases property stack") = forAll(propertyCard, game)((c, g) => {
    val p = g.players.head
    val (pA, gA) = PlayPropertyCard(c, p).build.run(g)
    pA.props(c) == p.props.getOrElse(c, 0) + 1

  })

}
