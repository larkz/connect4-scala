package connect4


import java.util.Collection
import scala.collection.mutable.MutableList
import collection.JavaConverters._
import java.util.Collection
import mcts.MDP

class Connect4MDP(startingState: Connect4State) extends MDP[Connect4State, Int] {

  override def actions(state: Connect4State): Collection[Int] = {
    val buf = scala.collection.mutable.ListBuffer.empty[Int]
    buf.toList.asJava
  }

  override def initialState(): Connect4State = {
    startingState
  }

  override def isTerminal(state: Connect4State): Boolean = {
    true
  }

  override def transition(state: Connect4State, action: Int): Connect4State ={
    startingState
  }

  // How do the nullable types get mapped over from Kotlin ?
  override def reward(previousState: Connect4State, action: Int, state: Connect4State): Double = {
    1.0
  }

}
