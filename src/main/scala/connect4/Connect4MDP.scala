package connect4


import java.util.Collection
import scala.collection.mutable.MutableList

// import collection.JavaConverters._
import java.util.Collection
import mcts.MDP
import collection.JavaConversions._

// Always optimize for player
class Connect4MDP(controller: Connect4Class) extends MDP[Connect4State, Int] {

  val startingState = new Connect4State(controller.connect4Grid.map(_.clone))

  override def actions(state: Connect4State): Collection[Int] = {
    val actionIndex  = controller.legalActions
    // val buf = scala.collection.mutable.ListBuffer.empty[Int]
    val actionIndexJava: java.util.Collection[Int] = actionIndex.toSeq
    actionIndexJava //.toList.asJava
  }

  override def initialState(): Connect4State = {
    startingState
  }

  override def isTerminal(state: Connect4State): Boolean = {

    if (controller.connect4Grid.forall(_.forall(_ > 0))) {
      controller.resetBoard()
      true
    }
    else if (controller.checkVictory(1) || controller.checkVictory(2)){
      controller.resetBoard()
      true
    } else {
      false
    }
  }

  override def transition(state: Connect4State, action: Int): Connect4State = {
    controller.playAction(action)
    new Connect4State(controller.connect4Grid.map(_.clone))
  }

  // How do the nullable types get mapped over from Kotlin ?
  // Player1 is always human, for now
  override def reward(previousState: Connect4State, action: Int, state: Connect4State): Double = {
    if (controller.checkVictory(1, state.grid)) {
      return 1.0
    } else if (controller.checkVictory(2, state.grid)){
      return -1.0
    }
    return 0.0
  }
}
