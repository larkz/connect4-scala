package connect4


import java.util.Collection
import scala.collection.mutable.MutableList

// import collection.JavaConverters._
import java.util.Collection
import mcts.MDP
import collection.JavaConversions._


class Connect4MDP(startingState: Connect4State,
                  optimizeForPlayerOne: Boolean) extends MDP[Connect4State, Int] {

  val controller = new Connect4Class()

  override def actions(state: Connect4State): Collection[Int] = {
    val actionIndex  = 0 to 6 toArray
    // val buf = scala.collection.mutable.ListBuffer.empty[Int]
    val actionIndexJava: java.util.Collection[Int] = actionIndex.toSeq
    actionIndexJava //.toList.asJava
  }

  override def initialState(): Connect4State = {
    startingState
  }

  override def isTerminal(state: Connect4State): Boolean = {
    state.grid.forall(_.forall(_ > 0))
  }

  override def transition(state: Connect4State, action: Int): Connect4State ={
    controller.playAction(action)
    new Connect4State(controller.connect4Grid)
  }

  // How do the nullable types get mapped over from Kotlin ?
  // Player1 is always human, for now
  override def reward(previousState: Connect4State, action: Int, state: Connect4State): Double = {

    if (controller.checkVictory(1)) {
      return 1.0
    }
    0.0
  }

}
