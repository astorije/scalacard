import scala.language.higherKinds

package object scalacard {

	type Hand = List[Card]
  type Deck = List[Card]
  type Bank = List[Money] // need to have logic that converts to Money
  type Discarded = List[Card]
  type Properties = Map[Property, Int]
  type MoneyPile = List[Money]

  val propertyLess = Map[Property, Int]()

  trait Monad[M[_]] {
    def zero[A](a: => A): M[A]
    def flatMap[A,B](ma: M[A])(f: A => M[B]): M[B]
    def map[A, B](ma: M[A])(f: A => B): M[B] =
      flatMap(ma)(a => zero(f(a)))
  }

  /**
   * State monad threads state through a computation, allowing updates at each stage.
   * Ultimately it makes sense to pull in a library like Scalaz or Cats, but I don't think we need that yet..
   * @param run
   * @tparam C
   * @tparam A
   */
  case class State[C, A](run: C => (A, C))
  object State{
    def stateM[C] = new Monad[({type l[x] = State[C, x]})#l] {
      def zero[A](a: => A): State[C, A] = State(s => (a, s))
      def flatMap[A, B](ma: State[C, A])(f: A => State[C, B]) =
        State(s => {
          val (a, state) = ma.run(s)
          f(a).run(state)
        })
    }
  }

  /**
   * This is very simmilar to the state monad except the state is read-only. I'm not sure this is necessary
   * for MonopolyDeal, but it may come in handy.
   * @param run
   * @tparam C
   * @tparam A
   */
  case class Reader[C, A](run: C => A)
  object Reader {
    def readerM[C] = new Monad[({type l[x] = Reader[C, x]})#l] {
      def zero[A](a: => A): Reader[C, A] = Reader(_ => a)
      def flatMap[A, B](r: Reader[C, A])(f: A => Reader[C, B]): Reader[C, B] =
        Reader(rd => f(r.run(rd)).run(rd))
    }
  }


}
