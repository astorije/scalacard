package scalacard

import scala.util.Random

object Gameplay {

  lazy val newDeck = List.fill(3)(One) ++
    	List.fill(3)(Two) ++
      (Five :: Ten :: Nil)

  def newGame(players: Int) = {
    val ps = Vector.fill(players)(Player(Nil, propertyLess, Nil))

    Game(ps, Random.shuffle(newDeck))
  }


}
