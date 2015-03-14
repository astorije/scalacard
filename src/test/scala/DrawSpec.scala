package scalacard

import org.scalacheck.Prop._
import org.scalacheck.{Properties => SProp}
import Generators._

object DrawSpec extends SProp("Draw"){

  property("noops on empty deck") = forAll(game){g =>
    Draw(2, g.players.head).build.run(g.copy(deck = Nil)) == (g.players.head, g.copy(deck = Nil))
  }

  property("standard deal adds n cards to player hand & pops n from deck") = forAll(game)(g => {
    val n = 2
    val player = g.players.head
    val (pAfter, gAfter) = Draw(n, player).build.run(g)
    pAfter.hand.length == player.hand.length + n &&
    gAfter.deck.length == g.deck.length - n ||
    g.deck.length < n
  })

  property("only the drawing player's hand changes") = forAll(game)(g => {
    val player = g.players.head
    val (pAfter, gAfter) = Draw(2, player).build.run(g)

    gAfter.players.tail.zip(g.players.tail).forall(t => t._1.hand.length == t._2.hand.length)
  })

}
