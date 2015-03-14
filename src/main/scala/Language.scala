package scalacard

//note could probably specialize this to player
sealed trait GameAction[A] {
  def build: State[Game,A]
}

case class Pass(p: Player) extends GameAction[Player] {
 def build = State(s =>
   (p, s)
 )
}

case class Draw(n: Int, p: Player) extends GameAction[Player] {
  def build = State(s => {
    s.deck match {
      //note todo collapse these two cases into a single block
      case ls if s.deck.length < n =>
        val newHand = ls ::: p.hand
        val newPlayer = p.copy(hand = newHand)
        (newPlayer, Game(newPlayer +: s.players.drop(1), Nil, s.discarded))
      case ls if s.deck.length >= n =>
        val newHand = ls.take(n) ::: p.hand
        val newPlayer = p.copy(hand = newHand)
        (newPlayer, Game(newPlayer +: s.players.tail, s.deck.drop(n), s.discarded))
      case Nil =>
        (p, s)
    }
  })}

//case object EndTurn extends GameAction


case class PlayMoneyCard(m: Money, p: Player) extends GameAction[Player] {
 def build = State(g => {
     val newPlayer = p.copy(cash = m :: p.cash)
     val newGame = g.copy(players = newPlayer +: g.players.tail)
     (newPlayer, newGame)
 })
}

// Still need resolution rules for property wildcards
case class PlayPropertyCard(prop: Property, p: Player) extends GameAction[Player] {
  def build = State(g => {
    val newProps = p.props.get(prop)
      .fold[Properties](p.props + (prop -> 1))(i => p.props + (prop -> (i + 1)))
    val newPlayer = p.copy(props = newProps)
    val newGame = g.copy(players = newPlayer +: g.players.tail)
    (newPlayer, newGame)
  })
}

case class PlayCard(c: Card, p: Player) extends GameAction[Player] { //note this is a bs return type for now
  def build = State(g => {
    c match {
//      case a: Action
//      case r: Rent
//      case p: Property
//      case w: PropertyWildcard => (p, g)
      case _ => (p, g)
    }
  })
}






