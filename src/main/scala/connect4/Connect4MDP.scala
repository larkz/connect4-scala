package connect4


import java.util.Collection
import scala.collection.mutable.MutableList

// import collection.JavaConverters._
import java.util.Collection
import mcts.MDP
import collection.JavaConversions._

// Always optimize for player
class Connect4MDP(startingState: Connect4State) extends MDP[Connect4State, Int] {

  // val startingState = new Connect4State(controller.connect4Grid.map(_.clone))

  override def actions(state: Connect4State): Collection[Int] = {
    startingState.grid(0).indices.toList
  }

  override def initialState(): Connect4State = {
    val initialState = new Connect4State(startingState.grid)
    initialState
  }

  override def isTerminal(state: Connect4State): Boolean = {
    val controller = new Connect4Class(state.grid.map(_.clone))

    if (controller.checkVictory(1, state.grid)) {controller.resetBoard(); return true}
    if (controller.checkVictory(2, state.grid)) {controller.resetBoard(); return true}
    if (state.grid.forall(_.forall(_ > 0))) {controller.resetBoard(); return true}

    false
  }

  override def transition(state: Connect4State, action: Int): Connect4State = {
    val controller = new Connect4Class(state.grid, state.playerTurn)
    controller.playAction(action)
    val nextState = if (controller.inducedPlayerTurn == 1) new Connect4State(controller.connect4Grid.map(_.clone)) else new Connect4State(controller.connect4Grid.map(_.clone), 2)
    nextState
  }

  // How do the nullable types get mapped over from Kotlin ?
  // Player1 is always human, for now
  override def reward(previousState: Connect4State, action: Int, state: Connect4State): Double = {
    val controller = new Connect4Class(state.grid.map(_.clone), state.playerTurn)
    if (controller.checkVictory(1, state.grid.map(_.clone))) {return 1.0}
    if (controller.checkVictory(2, state.grid.map(_.clone))) {return -1.0}

    0.0
  }
}
