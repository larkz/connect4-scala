package connect4

import mctreesearch4j.{AdvancedStatelessSolver, MDP}

import java.util
import scala.collection.mutable
import scala.collection.JavaConverters._

class Connect4Solver (
  mdp: MDP[Connect4State, Int],
  simulationDepthLimit: Int,
  explorationConstant: Double,
  rewardDiscountFactor: Double,
  verbose: Boolean) {
  // extends AdvancedStatelessSolver[Connect4State, Int](mdp, simulationDepthLimit, explorationConstant, rewardDiscountFactor, verbose) {

  /*
  def getOptimalHorizon(): Iterable[Int] = {

    var node = super.getRoot
    var optimalHorizonArr = List[Int]()

    // var availActions = node.getValidActions
    while (  (node.getChildren(_) != null).toString().toBoolean )  {
      node = node.getChildren(_).asScala.toList.maxBy(_.getN).getParent
      optimalHorizonArr

    }
    availActions.asScala.toList.maxBy(   )

  }

   */


}
